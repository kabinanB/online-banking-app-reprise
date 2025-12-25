package org.onlinebankingapp.service

import org.onlinebankingapp.entity.{Account, AccountType, User}

import java.util.Optional

trait AccountService {

  // =========================
  // ADMIN METHODS
  // =========================
  def getAllAccounts(): java.util.List[Account]

  def getAccountById(id: Long): Optional[Account]

  def createAccount(account: Account): Account

  def updateAccount(id: Long, account: Account): Account

  def deleteAccount(id: Long): Boolean

  // =========================
  // USER METHODS
  // =========================
  def getAccountsForUser(user: User): java.util.List[Account]

  def getAccountForUserById(id: Long, user: User): Optional[Account]

  def createAccountForUser(
                            user: User,
                            accountType: AccountType
                          ): Account

}
