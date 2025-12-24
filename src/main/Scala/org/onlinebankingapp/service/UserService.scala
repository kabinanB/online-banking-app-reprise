package org.onlinebankingapp.service

import org.onlinebankingapp.entity.User

import java.util.List

trait UserService {

  def getUserOrThrow(id: Long): User

  def getAllUsers(): List[User]

  def deleteUser(id: Long): Unit
}
