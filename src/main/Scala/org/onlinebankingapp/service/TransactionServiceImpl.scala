package org.onlinebankingapp.service.impl

import org.onlinebankingapp.entity.{Transaction, TransactionStatus, User}
import org.onlinebankingapp.repository.{AccountRepo, TransactionRepo}
import org.onlinebankingapp.service.TransactionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.time.LocalDateTime
import java.util.Optional
import java.util

@Service
class TransactionServiceImpl @Autowired() (
                                            transactionRepo: TransactionRepo,
                                            accountRepo: AccountRepo
                                          ) extends TransactionService {

  override def getAllTransactions(): util.List[Transaction] =
    transactionRepo.findAll()

  override def getTransactionById(id: Long): Optional[Transaction] =
    transactionRepo.findById(id)

  override def getTransactionsForUser(user: User): util.List[Transaction] =
    transactionRepo.findByFromAccount_UserOrToAccount_User(user, user)

  override def createTransaction(
                                  transaction: Transaction,
                                  user: User
                                ): Transaction = {

    val fromAccount = transaction.getFromAccount

    if (fromAccount.getUser.getId != user.getId)
      throw new RuntimeException("You can only send money from your own account")

    transaction.setStatus(TransactionStatus.PENDING)
    transactionRepo.save(transaction)
  }


  @Transactional
  override def approveTransaction(transactionId: Long): Transaction = {
    val tx = transactionRepo.findById(transactionId)
      .orElseThrow(() => new RuntimeException("Transaction not found"))

    if (tx.getStatus != TransactionStatus.PENDING)
      throw new RuntimeException("Only PENDING transactions can be approved")

    val from = tx.getFromAccount
    val to = tx.getToAccount
    val amount = tx.getAmount

    if (from.getBalance < amount)
      throw new RuntimeException("Insufficient balance")

    from.setBalance(from.getBalance - amount)
    to.setBalance(to.getBalance + amount)

    accountRepo.save(from)
    accountRepo.save(to)

    tx.setStatus(TransactionStatus.APPROVED)
    transactionRepo.save(tx)
  }

  override def rejectTransaction(transactionId: Long): Transaction = {
    val tx = transactionRepo.findById(transactionId)
      .orElseThrow(() => new RuntimeException("Transaction not found"))

    if (tx.getStatus != TransactionStatus.PENDING)
      throw new RuntimeException("Only PENDING transactions can be rejected")

    tx.setStatus(TransactionStatus.REJECTED)
    transactionRepo.save(tx)
  }

  override def getTransactionForUserById(
                                          transactionId: Long,
                                          user: User
                                        ): Optional[Transaction] = {

    transactionRepo.findByIdAndUser(transactionId, user)
  }
}
