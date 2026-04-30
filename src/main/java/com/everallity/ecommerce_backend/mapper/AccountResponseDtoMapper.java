package com.everallity.ecommerce_backend.mapper;

import com.everallity.ecommerce_backend.model.Account;
import com.everallity.ecommerce_backend.model.Role;
import com.everallity.ecommerce_backend.model.dto.response.AccountResponseDto;

import java.util.stream.Collectors;

public class AccountResponseDtoMapper {
    public AccountResponseDto accountToAccountResponseDto(Account account) {
        AccountResponseDto accountResponseDto = new AccountResponseDto();
        accountResponseDto.setPassword(account.getPasswordHash());
        accountResponseDto.setEmail(account.getEmail());
        accountResponseDto.setId(account.getId());
        accountResponseDto.setRoleSet(account.getRoleSet()
                .stream()
                .map(Role::getName)
                .collect(Collectors.toSet())
        );
        return accountResponseDto;
    }
}
