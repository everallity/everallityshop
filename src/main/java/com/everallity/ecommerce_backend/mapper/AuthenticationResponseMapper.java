package com.everallity.ecommerce_backend.mapper;

import com.everallity.ecommerce_backend.model.Account;
import com.everallity.ecommerce_backend.model.Role;
import com.everallity.ecommerce_backend.model.dto.response.AuthenticationResponseDto;

import java.util.stream.Collectors;

public class AuthenticationResponseMapper {

    public AuthenticationResponseMapper() {
    }

    public AuthenticationResponseDto AccountToAuthenticationResponseDto(Account account, String token) {
        AuthenticationResponseDto authenticationResponseDto = new AuthenticationResponseDto();
        authenticationResponseDto.setEmail(account.getEmail());
        authenticationResponseDto.setToken(token);
        authenticationResponseDto.setRoles(account.getRoleSet().stream().map(Role::getName).collect(Collectors.toSet()));
        return authenticationResponseDto;
    }
}
