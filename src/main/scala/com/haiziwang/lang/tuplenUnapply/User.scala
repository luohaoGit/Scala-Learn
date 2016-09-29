package com.haiziwang.lang.tuplenUnapply

import com.typesafe.scalalogging.slf4j.StrictLogging

/**
  * Created by luohao on 2016/9/29.
  */
trait User {
  def name: String
  def score: Int
}
class FreeUser(val name: String, val score: Int, val upgradeProbability: Double) extends User
class PremiumUser(val name: String, val score: Int) extends User
class premiumCandidate(val name: String, val score: Int) extends User

//unapply返回元组
object FreeUser {
  def unapply(user: FreeUser): Option[(String, Int, Double)] =
    Some((user.name, user.score, user.upgradeProbability))

  //布尔提取器
  //def unapply(user: FreeUser): Boolean = user.upgradeProbability > 0.75
}
object PremiumUser {
  def unapply(user: PremiumUser): Option[(String, Int)] =
    Some((user.name, user.score))
}

object premiumCandidate {
  def unapply(user: FreeUser): Boolean = user.upgradeProbability > 0.75
}

object Test extends App with StrictLogging {

  val user: User = new FreeUser("Daniel", 3000, 0.7d)
  user match {
    case FreeUser(name, _, p) =>
      if (p > 0.75) logger.info(s"$name, what can we do for you today?")
      else logger.info(s"Hello $name")
    case PremiumUser(name, _) =>
      logger.info(s"Welcome back, dear $name")
  }

  val user1: User = new FreeUser("Daniel", 2500, 0.8d)
  user1 match {
    case freeUser @ premiumCandidate() => logger.info("initiateSpamProgram(freeUser)")
    case _ => logger.info("sendRegularNewsletter(user)")
  }

}