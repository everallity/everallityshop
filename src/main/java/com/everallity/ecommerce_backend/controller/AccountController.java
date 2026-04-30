package com.everallity.ecommerce_backend.controller;

import com.everallity.ecommerce_backend.mapper.AccountRequestDtoMapper;
import com.everallity.ecommerce_backend.mapper.AccountResponseDtoMapper;
import com.everallity.ecommerce_backend.model.Account;
import com.everallity.ecommerce_backend.model.dto.request.AccountRequestDto;
import com.everallity.ecommerce_backend.model.dto.response.AccountResponseDto;
import com.everallity.ecommerce_backend.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final AccountRequestDtoMapper accountRequestDtoMapper = new AccountRequestDtoMapper();
    private final AccountResponseDtoMapper accountResponseDtoMapper = new AccountResponseDtoMapper();

    public AccountController(AccountService accountService) {this.accountService = accountService;}

    @DeleteMapping("/delete")
    @Modifying
    public Boolean deleteAccount(@RequestBody AccountRequestDto accountRequestDto) {
        return accountService.deleteAccount(accountRequestDto);
    }

    @GetMapping
    public List<AccountResponseDto> getAccounts() {
        List<Account> accountList = accountService.getAccountList();
        List<AccountResponseDto> accountResponseDtoList = new ArrayList<>();
        for (Account account : accountList) {
            AccountResponseDto accountResponseDto = accountResponseDtoMapper.accountToAccountResponseDto(account);
            accountResponseDtoList.add(accountResponseDto);
        }
        return accountResponseDtoList;
    }

    @GetMapping("/me")
    public AccountResponseDto getOwnAccount(Authentication authentication) {
        Account account = accountService.getAccountByEmail(authentication.getName());
        return accountResponseDtoMapper.accountToAccountResponseDto(account);
    }
}
