package org.onlinebankingapp.service.impl

import org.onlinebankingapp.entity.{Account, AccountType, User}
import org.onlinebankingapp.repository.AccountRepo
import org.onlinebankingapp.service.AccountService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import java.util.{List, Optional}

@Service
class AccountServiceImpl @Autowired()(accountRepo: AccountRepo)
  extends AccountService {

  // =========================
  // ADMIN METHODS
  // =========================

  override def getAllAccounts(): List[Account] =
    accountRepo.findAll()

  override def getAccountById(id: Long): Optional[Account] =
    accountRepo.findById(id)

  override def createAccount(account: Account): Account =
    accountRepo.save(account)

  override def updateAccount(id: Long, account: Account): Account = {
    if (!accountRepo.existsById(id))
      throw new IllegalArgumentException("Account not found")

    account.setId(id)
    accountRepo.save(account)
  }

  override def deleteAccount(id: Long): Boolean = {
    if (accountRepo.existsById(id)) {
      accountRepo.deleteById(id)
      true
    } else {
      false
    }
  }

  // =========================
  // USER METHODS
  // =========================

  override def getAccountsForUser(user: User): List[Account] =
    accountRepo.findByUser(user)

  override def getAccountForUserById(
                                      id: Long,
                                      user: User
                                    ): Optional[Account] = {
    accountRepo.findById(id)
      .filter(account =>
        account.getUser.getId == user.getId
      )
  }

  override def createAccountForUser(
                                     user: User,
                                     accountType: AccountType
                                   ): Account = {

    val existing =
      accountRepo.findByUserAndAccountType(user, accountType)

    if (existing.isPresent)
      throw new IllegalStateException(
        s"${accountType.name()} account already exists for user"
      )

    val account = new Account()
    account.setUser(user)
    account.setAccountType(accountType)
    account.setBalance(BigDecimal.decimal(0.0))

    accountRepo.save(account)
  }

}
