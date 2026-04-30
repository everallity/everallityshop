package com.everallity.ecommerce_backend.service.jwt;

import com.everallity.ecommerce_backend.model.Account;
import com.everallity.ecommerce_backend.model.dto.request.TokenValidateRequestDto;
import com.everallity.ecommerce_backend.model.dto.response.TokenValidateResponseDto;
import com.nimbusds.jose.JOSEException;

public interface JwtServiceInterface {
    String generateJwtToken(Account account) throws JOSEException;
    TokenValidateResponseDto validateToken(TokenValidateRequestDto tokenValidateRequestDto);
    String extractUsername(String token);
    Boolean isExpired(String token);
    Object extractClaim(String token);
}
