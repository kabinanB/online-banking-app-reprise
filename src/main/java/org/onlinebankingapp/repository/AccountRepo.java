package org.onlinebankingapp.repository;

import org.onlinebankingapp.entity.Account;
import org.onlinebankingapp.entity.Transaction;
import org.onlinebankingapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepo extends JpaRepository<Account, Long> {

}
