package org.onlinebankingapp.service

import org.onlinebankingapp.entity.{Transaction, User}

import java.util.Optional


trait TransactionService {

  def getAllTransactions(): java.util.List[Transaction]

  def getTransactionById(id: Long): Optional[Transaction]

  def getTransactionsForUser(user: User): java.util.List[Transaction]

  def createTransaction(
                                  transaction: Transaction,
                                  user: User
                                ): Transaction

  def approveTransaction(transactionId: Long): Transaction

  def rejectTransaction(transactionId: Long): Transaction

  def getTransactionForUserById(
                                 transactionId: Long,
                                 user: User
                               ): Optional[Transaction]

}
