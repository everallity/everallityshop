package com.everallity.ecommerce_backend.service;

import com.everallity.ecommerce_backend.constant.ErrorCode;
import com.everallity.ecommerce_backend.exception.AppException;
import com.everallity.ecommerce_backend.mapper.AccountRequestDtoMapper;
import com.everallity.ecommerce_backend.model.Account;
import com.everallity.ecommerce_backend.model.dto.request.AccountRequestDto;
import com.everallity.ecommerce_backend.repository.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    private static final Logger log = LoggerFactory.getLogger(AccountService.class);
    private final AccountRepository accountRepository;
    private final AccountRequestDtoMapper accountRequestDtoMapper = new AccountRequestDtoMapper();

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @PreAuthorize("hasRole('ADMIN') or @accountService.isOwnerRequest(#accountRequestDto.email, authentication.name)")
    public Boolean deleteAccount(AccountRequestDto accountRequestDto) {
        Account targetAccount = getAccount(accountRequestDto);
        accountRepository.delete(targetAccount);
        return true;
    }

    public boolean isOwnerRequest(String email, String authName) {
        return email.equals(authName);
    }

    @PreAuthorize("hasRole('ADMIN') or @accountService.isOwnerRequest(#email, authentication.name)")
    public Account getAccountByEmail(String email) {
        return accountRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
    }


    @PreAuthorize("hasRole('ADMIN') or @accountService.isOwnerRequest(#accountRequestDto.email, authentication.name)")
    public Account getAccount(AccountRequestDto accountRequestDto) {
        Account account = accountRequestDtoMapper.accountRequestDtoToAccountMap(accountRequestDto);
        return accountRepository.findByEmail(account.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.ACCOUNT_NOT_FOUND));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<Account> getAccountList() {
        var name = SecurityContextHolder.getContext().getAuthentication().getName();
        var roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        log.info("name = {}", name);
        log.info("roles = {}", roles);
        return accountRepository.findAll();
    }
}
