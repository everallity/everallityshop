package com.everallity.ecommerce_backend.mapper;

import com.everallity.ecommerce_backend.entity.Account;
import com.everallity.ecommerce_backend.entity.dto.request.AccountRequestDto;

public class AccountRequestDtoMapper {
    public Account accountRequestDtoToAccountMap(AccountRequestDto accountRequestDto) {
        Account account = new Account();
        account.setEmail(accountRequestDto.getEmail());
        account.setPasswordHash(accountRequestDto.getPassword());
        return account;
    }
}
