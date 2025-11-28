package org.onlinebankingapp.repository;

import org.onlinebankingapp.entity.Account;
import org.onlinebankingapp.entity.Transaction;
import org.onlinebankingapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {

}
