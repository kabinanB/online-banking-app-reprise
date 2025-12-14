package org.onlinebankingapp.service

import org.onlinebankingapp.entity.Account
import org.springframework.stereotype.Service

import java.util.Optional

trait AccountService {

  def getAllAccounts(): java.util.List[Account]

  def getAccountById(id: Long): Optional[Account]

  def createAccount(account: Account): Account

  def updateAccount(id: Long, account: Account): Account

  def deleteAccount(id: Long): Boolean

}
