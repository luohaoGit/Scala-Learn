package com.haiziwang.lang.unapply

import com.typesafe.scalalogging.slf4j.StrictLogging

/**
  * Created by luohao on 2016/9/29.
  */
object Test extends App with StrictLogging{

  val r: Option[String] = FreeUser.unapply(new FreeUser("Daniel"))

  logger.info(r.get)

  val user: User = new PremiumUser("luohao")
  user match {
    case FreeUser(name) => logger.info(s"Hello $name")
    case PremiumUser(name) => logger.info(s"Welcome back, dear $name")
  }
}
