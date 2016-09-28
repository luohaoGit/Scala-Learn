package com.haiziwang.actor.cakePatternSimple

/**
  * Created by luohao on 2016/9/28.
  */
trait UserService {
  def findAll: List[User]
}

trait UserRepository {
  def loadUser: List[User]
}

trait TransactionManager {
  def executeSQL(sql: String): List[User]
}

trait TransactionManagerImpl extends TransactionManager{
  override def executeSQL(sql: String): List[User] = ???
}

trait UserRepositoryImpl extends UserRepository{
  //this:X =>为自身类型，在实例化或定义子类的时候必须混入指定的X类型，这个X类型也可以指定为当前类型
  this: TransactionManager =>
  override def loadUser: List[User] = executeSQL("SELECT * FROM user")
}

//定义子类时用with混入TransactionManager
trait DefaultUserRepositoryImpl extends UserRepositoryImpl with TransactionManagerImpl
trait UserServiceImpl extends UserService {
  this: UserRepository =>
  override def findAll: List[User] = loadUser
}
