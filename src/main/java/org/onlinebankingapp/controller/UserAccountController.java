package org.onlinebankingapp.controller;

import org.onlinebankingapp.entity.Account;
import org.onlinebankingapp.entity.AccountType;
import org.onlinebankingapp.entity.User;
import org.onlinebankingapp.security.SecurityUtils;
import org.onlinebankingapp.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@PreAuthorize("hasRole('USER')")
public class UserAccountController {

    private final AccountService accountService;

    public UserAccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // =========================
    // GET ALL USER ACCOUNTS
    // =========================
    @GetMapping
    public ResponseEntity<List<Account>> getMyAccounts() {
        User currentUser = getCurrentUser();
        return ResponseEntity.ok(accountService.getAccountsForUser(currentUser));
    }

    // =========================
    // GET USER ACCOUNT BY ID
    // =========================
    @GetMapping("/{id}")
    public ResponseEntity<Account> getMyAccountById(@PathVariable Long id) {
        User currentUser = getCurrentUser();
        return accountService.getAccountForUserById(id, currentUser)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // =========================
    // CREATE ACCOUNT (OPTIONAL)
    // =========================
    @PostMapping("/accounts/{type}")
    public ResponseEntity<Account> createAccount(
            @PathVariable AccountType type
    ) {
        User currentUser = getCurrentUser();
        Account account = accountService.createAccountForUser(currentUser, type);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }


    // =========================
    // HELPER
    // =========================
    private User getCurrentUser() {
        // you already have this pattern in UserTransactionController
        // this should read from SecurityContext
        return SecurityUtils.getCurrentUser();
    }
}
