package com.haiziwang.actor.concurrency.akka.pingpong

import akka.actor.{Actor, ActorLogging, ActorRef}

/**
  * Created by luohao on 2016/9/28.
  */
case class Start(opponent: ActorRef)

class Ping extends Actor with ActorLogging{
  var count = 0

  override def receive: Receive = {
    case Start(opponent) => {
      log.info("starting...")
      opponent ! "Ping!"
    }
    case "Pong!" => {
      log.info("Pong! received")
      count += 1
      if(count < 10) sender() ! "Ping!"
      else context become tired
    }
  }

  def tired: Receive = {
    case _ =>
  }
}

