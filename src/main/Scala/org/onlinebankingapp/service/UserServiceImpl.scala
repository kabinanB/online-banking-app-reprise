package org.onlinebankingapp.service

import org.onlinebankingapp.entity.User
import org.onlinebankingapp.repository.UserRepo
import org.springframework.stereotype.Service

import java.util.List

@Service
class UserServiceImpl(private val userRepo: UserRepo) extends UserService {

  override def getUserOrThrow(id: Long): User = {
    userRepo.findById(id)
      .orElseThrow(() => new IllegalArgumentException("User not found"))
  }

  override def getAllUsers(): List[User] = {
    userRepo.findAll()
  }

  override def deleteUser(id: Long): Unit = {
    if (!userRepo.existsById(id)) {
      throw new IllegalArgumentException("User not found")
    }
    userRepo.deleteById(id)
  }
}
