package org.onlinebankingapp.service

import org.onlinebankingapp.entity.User

import java.util

trait UserService {

  // Basic CRUD
  def createUser(user: User): User
  def getUserOrThrow(id: Long): User
  def getAllUsers(): util.List[User]
  def updateUser(user: User): User
  def deleteUser(id: Long): Boolean

  // Extended methods for current user
  def createUserWithRole(user: User, role: String): User
  def getCurrentAuthenticatedUser(): User
  def updateCurrentAuthenticatedUser(user: User): User
  def changePassword(oldPassword: String, newPassword: String): Unit
}
