package org.onlinebankingapp.repository;

import org.onlinebankingapp.entity.Transaction;
import org.onlinebankingapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    // All outgoing transactions
    List<Transaction> findByFromAccount_User(User user);

    // All incoming transactions
    List<Transaction> findByToAccount_User(User user);

    // All transactions (either direction)
    List<Transaction> findByFromAccount_UserOrToAccount_User(User user1, User user2);

}
