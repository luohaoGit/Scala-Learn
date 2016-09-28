package com.haiziwang.actor.concurrency.akka.pingpong

import akka.actor.{Actor, ActorLogging}

/**
  * Created by luohao on 2016/9/28.
  */
class Pong extends Actor with ActorLogging {
  override def receive: Receive = {
    case "Ping!" => {
      log.info("Ping! received")
      sender() ! "Pong!"
    }
  }
}
