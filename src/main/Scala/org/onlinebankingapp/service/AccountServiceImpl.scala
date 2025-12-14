package org.onlinebankingapp.service.impl

import org.onlinebankingapp.entity.Account
import org.onlinebankingapp.repository.AccountRepo
import org.onlinebankingapp.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.Optional
import java.util

@Service
class AccountServiceImpl @Autowired()(accountRepo: AccountRepo) extends AccountService {

  // Get all accounts
  override def getAllAccounts(): util.List[Account] = {
    accountRepo.findAll()
  }

  // Get account by ID
  override def getAccountById(id: Long): Optional[Account] = {
    accountRepo.findById(id)
  }

  // Create a new account
  override def createAccount(account: Account): Account = {
    accountRepo.save(account)
  }

  // Update an existing account
  override def updateAccount(id: Long, account: Account): Account = {
    if(accountRepo.existsById(id)) {
      account.setId(id)  // Ensure the account has the correct ID
      accountRepo.save(account)
    } else {
      throw new IllegalArgumentException("Account not found")
    }
  }

  // Delete an account
  override def deleteAccount(id: Long): Boolean = {
    if(accountRepo.existsById(id)) {
      accountRepo.deleteById(id)
      true
    } else {
      false
    }
  }
}
