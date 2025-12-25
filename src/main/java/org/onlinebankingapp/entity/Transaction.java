package org.onlinebankingapp.entity;

import jakarta.persistence.*;
import scala.math.BigDecimal$;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Account getToAccount() {
        return toAccount;
    }

    public void setToAccount(Account toAccount) {
        this.toAccount = toAccount;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
