package org.onlinebankingapp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                      // Primary key

    @ManyToOne
    @JoinColumn(name = "fromAccount") // FK to Account (sender)
    private Account fromAccount;

    @ManyToOne
    @JoinColumn(name = "toAccount")   // FK to Account (receiver)
    private Account toAccount;

    private Double amount;                 // Transaction amount

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;      // PENDING, APPROVED, REJECTED, FAILED

    private LocalDateTime timestamp; // When transaction was created

    public Transaction() {
    }

    public Transaction(Long id) {
        this.id = id;
    }
}
