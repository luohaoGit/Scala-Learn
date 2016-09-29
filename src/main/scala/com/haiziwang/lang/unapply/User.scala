package com.haiziwang.lang.unapply

/**
  * Created by luohao on 2016/9/29.
  */
trait User {
  def name: String
}

class FreeUser(val name: String) extends User
class PremiumUser(val name: String) extends User

//伴生对象实现unapply相当于定义case class
object FreeUser {
  def unapply(user: FreeUser): Option[String] = Some(user.name)
}
object PremiumUser {
  def unapply(user: PremiumUser): Option[String] = Some(user.name)
}