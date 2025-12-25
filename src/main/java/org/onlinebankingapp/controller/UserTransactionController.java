package org.onlinebankingapp.controller;

import org.onlinebankingapp.entity.Transaction;
import org.onlinebankingapp.entity.User;
import org.onlinebankingapp.security.CustomUserDetails;
import org.onlinebankingapp.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class UserTransactionController {

    private final TransactionService transactionService;

    public UserTransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    // =========================
    // CREATE TRANSACTION
    // =========================
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(
            @RequestBody Transaction transaction
    ) {
        User currentUser = getCurrentUser();
        Transaction created =
                transactionService.createTransaction(transaction, currentUser);

        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // =========================
    // GET MY TRANSACTIONS
    // =========================
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/me")
    public ResponseEntity<List<Transaction>> getMyTransactions() {

        User currentUser = getCurrentUser();
        List<Transaction> transactions =
                transactionService.getTransactionsForUser(currentUser);

        return ResponseEntity.ok(transactions);
    }

    // =========================
    // GET TRANSACTION BY ID (OWN ONLY)
    // =========================
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {

        User currentUser = getCurrentUser();

        return transactionService
                .getTransactionForUserById(id, currentUser)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // =========================
    // HELPER
    // =========================
    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("User is not authenticated");
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof CustomUserDetails)) {
            throw new RuntimeException("Invalid authentication principal");
        }

        CustomUserDetails userDetails = (CustomUserDetails) principal;
        return userDetails.getUser();
    }
}
