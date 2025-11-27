package org.onlinebankingapp.service

import org.onlinebankingapp.entity.User

import java.util.Optional

trait UserService {

  def getUser(id: Long) : Optional[User]

  def createUser(user: User) : Optional[User]

  def getAllUsers() : java.util.List[User]

  def updateUser(user: User) : Optional[User]

  def deleteUser(id: Long) : Boolean


}
