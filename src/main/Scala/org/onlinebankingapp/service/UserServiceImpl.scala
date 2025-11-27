package org.onlinebankingapp.service

import org.onlinebankingapp.entity.User
import org.onlinebankingapp.repository.UserRepo
import org.springframework.beans.factory.annotation.Autowired

import java.util
import java.util.Optional

class UserServiceImpl @Autowired() (val userRepo: UserRepo) extends UserService {

  override def getUser(id: Long): Optional[User] = {
    val user = userRepo.findById(id)

    if (!user.isPresent) {
      throw new Exception(s"User not found!")
    }

    user
  }

  override def createUser(user: User): Optional[User] = {
    if (user.getName == null || user.getName.trim.isEmpty) {
      throw new IllegalArgumentException("User name cannot be empty")
    }

    val existing = userRepo.findById(user.getId)

    if (existing.isPresent) {
      throw new Exception(s"User with ID ${user.getId} already exists")
    }

    Optional.of(userRepo.save(user))
  }

  override def getAllUsers(): util.List[User] = {
    userRepo.findAll()
  }

  override def updateUser(user: User): Optional[User] = {

    if(user.getName == null || user.getName.trim.isEmpty){
      throw new IllegalArgumentException("User name cannot be empty")
    }

    Optional.of(userRepo.save(user))


  }

  override def deleteUser(id: Long): Boolean = {
    userRepo.deleteById(id)

    val user = userRepo.findById(id)

    !user.isPresent
  }
}