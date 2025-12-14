package org.onlinebankingapp.controller;

import org.onlinebankingapp.entity.Transaction;
import org.onlinebankingapp.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/transactions")
public class AdminTransactionController {

    @Autowired
    private TransactionService transactionService;

    // Get all transactions (admin view)
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    // Approve transaction
    @PutMapping("/{id}/approve")
    public ResponseEntity<Transaction> approveTransaction(@PathVariable Long id) {
        Transaction approved = transactionService.approveTransaction(id);
        return ResponseEntity.ok(approved);
    }

    // Reject transaction
    @PutMapping("/{id}/reject")
    public ResponseEntity<Transaction> rejectTransaction(@PathVariable Long id) {
        Transaction rejected = transactionService.rejectTransaction(id);
        return ResponseEntity.ok(rejected);
    }
}
