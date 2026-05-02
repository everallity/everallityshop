package com.everallity.ecommerce_backend.configuration;

import com.everallity.ecommerce_backend.constant.ErrorCode;
import com.everallity.ecommerce_backend.entity.Account;
import com.everallity.ecommerce_backend.entity.Role;
import com.everallity.ecommerce_backend.exception.AppException;
import com.everallity.ecommerce_backend.repository.AccountRepository;
import com.everallity.ecommerce_backend.repository.RoleRepository;
import com.everallity.ecommerce_backend.service.AccountService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
public class ApplicationInitConfiguration {


    @Bean
    ApplicationRunner applicationRunner(AccountRepository accountRepository, AccountService accountService, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        return args -> {
            if (accountRepository.findByEmail("admin@gmail.com").isEmpty()) {
                Account account = new Account();
                HashSet<Role> roles = new HashSet<>();
                Role defaultRole = roleRepository.findByName("ADMIN").orElseThrow(() -> new AppException(ErrorCode.INVALID_ROLE)) ;
                roles.add(defaultRole);
                account.setPasswordHash(passwordEncoder.encode("abcd1234"));
                account.setRoleSet(roles);
                account.setEmail("admin@gmail.com");
                accountRepository.save(account);
            }
        };
    }
}
