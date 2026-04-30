package com.everallity.ecommerce_backend.service.auth;

import com.everallity.ecommerce_backend.constant.ErrorCode;
import com.everallity.ecommerce_backend.exception.AppException;
import com.everallity.ecommerce_backend.mapper.AccountRequestDtoMapper;
import com.everallity.ecommerce_backend.mapper.AuthenticationResponseMapper;
import com.everallity.ecommerce_backend.model.Account;
import com.everallity.ecommerce_backend.model.Role;
import com.everallity.ecommerce_backend.model.dto.request.AccountRequestDto;
import com.everallity.ecommerce_backend.model.dto.response.AuthenticationResponseDto;
import com.everallity.ecommerce_backend.repository.AccountRepository;
import com.everallity.ecommerce_backend.service.AccountService;
import com.everallity.ecommerce_backend.service.jwt.JwtService;
import com.everallity.ecommerce_backend.service.role.RoleService;
import com.nimbusds.jose.JOSEException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    private final AuthenticationResponseMapper authenticationResponseMapper = new AuthenticationResponseMapper();
    private final AccountRequestDtoMapper accountRequestDtoMapper = new AccountRequestDtoMapper();
    private final AccountRepository accountRepository;
    private final RoleService roleService;


    public AuthenticationService(AccountRepository accountRepository, JwtService jwtService, RoleService roleService) {
        this.accountRepository = accountRepository;
        this.jwtService = jwtService;
        this.roleService = roleService;
    }


    public Account createAccount(AccountRequestDto accountRequestDto) {
        boolean duplicationCheck = accountRepository.findByEmail(accountRequestDto.getEmail()).isPresent();
        if (duplicationCheck) throw new AppException(ErrorCode.DUPLICATED_EMAIL);
        Role defaultRole = roleService.findRole("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(defaultRole);
        Account account = new Account();
        account.setEmail(accountRequestDto.getEmail());
        account.setPasswordHash(passwordEncoder.encode(accountRequestDto.getPassword()));
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        account.setRoleSet(roleSet);
        return accountRepository.save(account);
    }

    public AuthenticationResponseDto accountLogin(AccountRequestDto accountRequestDto) {
        Account accountCredential = accountRequestDtoMapper.accountRequestDtoToAccountMap(accountRequestDto);
        Account loginTarget = accountRepository.findByEmail(accountCredential.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.CREDENTIALS_INVALID));
        if (passwordEncoder.matches(accountCredential.getPasswordHash(), loginTarget.getPasswordHash()))
            try {
                String token = jwtService.generateJwtToken(loginTarget);

                return authenticationResponseMapper.AccountToAuthenticationResponseDto(loginTarget, token);
            } catch (JOSEException exception) {
                log.error(exception.getMessage());
                throw new AppException(ErrorCode.FAIL_JWT_TOKEN_GENERATION);
            }
        else throw new AppException(ErrorCode.CREDENTIALS_INVALID);
    }
}
