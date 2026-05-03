package com.everallity.ecommerce_backend.controller;

import com.everallity.ecommerce_backend.api.ApiResponse;
import com.everallity.ecommerce_backend.entity.dto.response.TokenValidateResponseDto;
import com.everallity.ecommerce_backend.mapper.AccountRequestDtoMapper;
import com.everallity.ecommerce_backend.mapper.AccountResponseDtoMapper;
import com.everallity.ecommerce_backend.entity.Account;
import com.everallity.ecommerce_backend.entity.dto.request.AccountRequestDto;
import com.everallity.ecommerce_backend.entity.dto.response.AccountResponseDto;
import com.everallity.ecommerce_backend.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ApiResponse<Boolean>> deleteAccount(@RequestBody AccountRequestDto accountRequestDto) {
        Boolean result = accountService.deleteAccount(accountRequestDto);
        ApiResponse<Boolean> response = new ApiResponse<>();
        response.setSuccess(true);
        response.setData(result);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<AccountResponseDto>>> getAccounts() {
        List<Account> accountList = accountService.getAccountList();
        List<AccountResponseDto> accountResponseDtoList = new ArrayList<>();
        for (Account account : accountList) {
            AccountResponseDto accountResponseDto = accountResponseDtoMapper.accountToAccountResponseDto(account);
            accountResponseDtoList.add(accountResponseDto);
        }
        ApiResponse<List<AccountResponseDto>> apiResponse = new ApiResponse<>();
        apiResponse.setData(accountResponseDtoList);
        apiResponse.setSuccess(true);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<AccountResponseDto>> getOwnAccount(Authentication authentication) {
        Account account = accountService.getAccountByEmail(authentication.getName());
        AccountResponseDto responseDto = accountResponseDtoMapper.accountToAccountResponseDto(account);
        ApiResponse<AccountResponseDto> apiResponse = new ApiResponse<>();
        apiResponse.setData(responseDto);
        apiResponse.setSuccess(true);
        return ResponseEntity.ok(apiResponse);
    }
}
