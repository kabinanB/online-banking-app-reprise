package org.onlinebankingapp.repository;

import org.onlinebankingapp.entity.Transaction;
import org.onlinebankingapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> findByUser(User user);

}
