package com.everallity.ecommerce_backend.controller;

import com.everallity.ecommerce_backend.constant.ErrorCode;
import com.everallity.ecommerce_backend.mapper.AccountResponseDtoMapper;
import com.everallity.ecommerce_backend.model.Account;
import com.everallity.ecommerce_backend.model.dto.request.AccountRequestDto;
import com.everallity.ecommerce_backend.model.dto.request.TokenValidateRequestDto;
import com.everallity.ecommerce_backend.model.dto.response.AccountResponseDto;
import com.everallity.ecommerce_backend.api.ApiResponse;
import com.everallity.ecommerce_backend.model.dto.response.AuthenticationResponseDto;
import com.everallity.ecommerce_backend.model.dto.response.TokenValidateResponseDto;
import com.everallity.ecommerce_backend.service.auth.AuthenticationService;
import com.everallity.ecommerce_backend.service.jwt.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;

    public AuthenticationController(AuthenticationService authenticationService, JwtService jwtService) {
        this.authenticationService = authenticationService;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<AccountResponseDto>> accountSignUp(@RequestBody @Valid AccountRequestDto accountRequestDto) {
        Account account = authenticationService.createAccount(accountRequestDto);
        ApiResponse<AccountResponseDto> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setData(new AccountResponseDtoMapper().accountToAccountResponseDto(account));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticationResponseDto>> login(@RequestBody AccountRequestDto accountRequestDto) {
        ApiResponse<AuthenticationResponseDto> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setData(authenticationService.accountLogin(accountRequestDto));
        return ResponseEntity.ok(response);
    }

    @PostMapping("/introspect")
    public ResponseEntity<ApiResponse<TokenValidateResponseDto>> tokenIntrospect(@RequestBody TokenValidateRequestDto tokenValidateRequestDto) {
        ApiResponse<TokenValidateResponseDto> response = new ApiResponse<>();
        TokenValidateResponseDto introspectResult = jwtService.validateToken(tokenValidateRequestDto);
        if (introspectResult.getValid()) {
            response.setData(introspectResult);
            response.setSuccess(true);
        } else {
            response.setErrorCode(ErrorCode.FAIL_JWT_TOKEN_VERIFICATION.getErrorCode());
            response.setMessage(ErrorCode.FAIL_JWT_TOKEN_VERIFICATION.getMessage());
            response.setData(introspectResult);
            response.setSuccess(false);
        }

        return ResponseEntity.ok(response);
    }

}
