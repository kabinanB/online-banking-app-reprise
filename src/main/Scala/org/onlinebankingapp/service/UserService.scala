package org.onlinebankingapp.service

import org.onlinebankingapp.entity.User

import java.util.List

trait UserService {

  def createUser(user: User): User

  def getUserOrThrow(id: Long): User

  def getAllUsers(): List[User]

  def updateUser(user: User): User

  def deleteUser(id: Long): Boolean
}
