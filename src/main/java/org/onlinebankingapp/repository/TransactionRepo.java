package org.onlinebankingapp.repository;

import org.onlinebankingapp.entity.Transaction;
import org.onlinebankingapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    // Outgoing transactions
    List<Transaction> findByFromAccount_User(User user);

    // Incoming transactions
    List<Transaction> findByToAccount_User(User user);

    // All transactions for a user
    List<Transaction> findByFromAccount_UserOrToAccount_User(User user1, User user2);

    // Transaction belongs to user (security-safe)
    Optional<Transaction> findByIdAndFromAccount_UserOrIdAndToAccount_User(
            Long id1, User user1,
            Long id2, User user2
    );
}

