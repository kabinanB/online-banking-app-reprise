package org.onlinebankingapp.service

import org.onlinebankingapp.entity.User
import org.onlinebankingapp.repository.UserRepo
import org.springframework.stereotype.Service

import java.util.List

@Service
class UserServiceImpl(private val userRepo: UserRepo) extends UserService {

  override def createUser(user: User): User = {
    // Optional: Add validation logic here
    // e.g., check if email already exists
    if (userRepo.findByUsername(user.getUsername).isPresent) {
      throw new IllegalArgumentException("User with this email already exists")
    }

    userRepo.save(user)
  }

  override def getUserOrThrow(id: Long): User = {
    userRepo.findById(id)
      .orElseThrow(() => new IllegalArgumentException("User not found"))
  }

  override def getAllUsers(): List[User] = {
    userRepo.findAll()
  }

  override def updateUser(user: User): User = {
    // Verify user exists first
    if (!userRepo.existsById(user.getId)) {
      throw new IllegalArgumentException("User not found")
    }
    userRepo.save(user)
  }

  override def deleteUser(id: Long): Boolean = {

    if(userRepo.existsById(id)){
      userRepo.deleteById(id)
      true
    } else {
      false
    }
  }
}
