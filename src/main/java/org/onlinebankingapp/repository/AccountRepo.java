package org.onlinebankingapp.repository;

import org.onlinebankingapp.entity.Account;
import org.onlinebankingapp.entity.AccountType;
import org.onlinebankingapp.entity.Transaction;
import org.onlinebankingapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    Optional<Account> findByUserAndAccountType(User user, AccountType accountType);

    List<Account> findByUser(User user);


}
