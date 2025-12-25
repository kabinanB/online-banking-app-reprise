package org.onlinebankingapp.service

import org.onlinebankingapp.entity.User
import org.onlinebankingapp.repository.UserRepo
import org.onlinebankingapp.security.CustomUserDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

import java.util
import scala.jdk.CollectionConverters._

@Service
class UserServiceImpl(private val userRepo: UserRepo) extends UserService {

  // Create user (admin or self-signup)
  override def createUser(user: User): User = {
    if (userRepo.findByUsername(user.getUsername).isPresent) {
      throw new IllegalArgumentException("User with this username already exists")
    }
    userRepo.save(user)
  }

  // Create user with a specific role (USER or ADMIN)
  def createUserWithRole(user: User, role: String): User = {
    user.setRole(role)
    userRepo.save(user)
  }

  // Get currently authenticated user
  def getCurrentAuthenticatedUser(): User = {
    val auth = SecurityContextHolder.getContext.getAuthentication
    val principal = auth.getPrincipal.asInstanceOf[CustomUserDetails]
    principal.getUser
  }

  // Get user by ID
  override def getUserOrThrow(id: Long): User = {
    userRepo.findById(id)
      .orElseThrow(() => new IllegalArgumentException("User not found"))
  }

  // Get all users
  override def getAllUsers(): util.List[User] = {
    userRepo.findAll()
  }

  // Update existing user
  override def updateUser(user: User): User = {
    if (!userRepo.existsById(user.getId)) {
      throw new IllegalArgumentException("User not found")
    }
    userRepo.save(user)
  }

  // Delete user
  override def deleteUser(id: Long): Boolean = {
    if (userRepo.existsById(id)) {
      userRepo.deleteById(id)
      true
    } else {
      false
    }
  }

  // Optional: Update current authenticated user profile
  def updateCurrentAuthenticatedUser(user: User): User = {
    val current = getCurrentAuthenticatedUser()
    current.setUsername(user.getUsername)
    current.setEmail(user.getEmail)
    // Update other fields as needed
    userRepo.save(current)
  }

  // Optional: Change password for current authenticated user
  def changePassword(oldPassword: String, newPassword: String): Unit = {
    val current = getCurrentAuthenticatedUser()
    if (!current.getPassword.equals(oldPassword)) {
      throw new IllegalArgumentException("Old password does not match")
    }
    current.setPassword(newPassword)
    userRepo.save(current)
  }
}
