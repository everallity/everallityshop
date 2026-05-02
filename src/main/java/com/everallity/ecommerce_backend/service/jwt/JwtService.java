package com.everallity.ecommerce_backend.service.jwt;

import com.everallity.ecommerce_backend.entity.Account;
import com.everallity.ecommerce_backend.entity.Role;
import com.everallity.ecommerce_backend.entity.dto.request.TokenValidateRequestDto;
import com.everallity.ecommerce_backend.entity.dto.response.TokenValidateResponseDto;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;
import java.util.StringJoiner;

@Service
public class JwtService implements JwtServiceInterface {

    @Value("${jwt.signerKey}")
    private String SECRET_KEY;
    private final long EXPIRATION_PERIOD = 15 * 60 * 1000;
    private final String ISSUER = "duc_khuat_ecommerce_backend";

    public String generateJwtToken(Account account) throws JOSEException {
        Date issueDate = new Date();
        MACSigner signer = new MACSigner(SECRET_KEY.getBytes());
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(account.getEmail())
                .issueTime(issueDate)
                .issuer(ISSUER)
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("scope", getScope(account))
                .build();
        SignedJWT signedJWT = new SignedJWT(jwsHeader, claimsSet);
        signedJWT.sign(signer);
        return signedJWT.serialize();
    }

    private String getScope(Account account) {
        Set<Role> roles = account.getRoleSet();
        StringJoiner stringJoiner = new StringJoiner(" ");
        roles.forEach(string -> {
            stringJoiner.add(string.getName());
        });
        return stringJoiner.toString();
    }

    public TokenValidateResponseDto validateToken(TokenValidateRequestDto tokenValidateRequestDto) {
        String token = tokenValidateRequestDto.getToken();
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            MACVerifier macVerifier = new MACVerifier(SECRET_KEY);
            var isTokenVerified = signedJWT.verify(macVerifier);

            if (!isTokenVerified) return new TokenValidateResponseDto(false);


            JWTClaimsSet claimsSet = signedJWT.getJWTClaimsSet();
            Date expirationDate = claimsSet.getExpirationTime();
            return new TokenValidateResponseDto(expirationDate.after(new Date()));
        } catch (Exception exception) {
           return new TokenValidateResponseDto(false);
        }

    }

    @Override
    public String extractUsername(String token) {
        return "";
    }

    @Override
    public Boolean isExpired(String token) {
        return null;
    }

    @Override
    public Object extractClaim(String token) {
        return null;
    }
}
