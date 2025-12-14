package org.onlinebankingapp.controller;

import org.onlinebankingapp.entity.Transaction;
import org.onlinebankingapp.entity.User;
import org.onlinebankingapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // =========================
    // USER ENDPOINTS
    // =========================

    // Create a transaction (PENDING)
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        Transaction created = transactionService.createTransaction(transaction);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // Get all transactions for a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Transaction>> getTransactionsForUser(@PathVariable Long userId) {
        User user = new User(userId); // lightweight reference
        List<Transaction> transactions = transactionService.getTransactionsForUser(user);
        return ResponseEntity.ok(transactions);
    }

    // Get transaction by ID
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        return transactionService.getTransactionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
