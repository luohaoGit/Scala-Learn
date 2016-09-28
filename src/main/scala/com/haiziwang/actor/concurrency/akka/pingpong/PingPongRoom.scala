package com.haiziwang.actor.concurrency.akka.pingpong

import akka.actor.{ActorSystem, Props}
import com.typesafe.scalalogging.slf4j.StrictLogging

/**
  * Created by luohao on 2016/9/28.
  */
object PingPongRoom extends App with StrictLogging {
  val system = ActorSystem("pingpang")
  val ping = system.actorOf(Props[Ping], "ping")
  val pong = system.actorOf(Props[Pong], "pong")
  ping ! Start(pong)
  logger.info("watching")
  Thread.sleep(2000)
  system.terminate()
}

