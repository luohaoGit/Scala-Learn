package com.haiziwang.actor.concurrency.akka.cloc

import akka.actor.{Actor, ActorLogging}

import scala.io.Source

/**
  * Created by luohao on 2016/9/28.
  */
case class Count(path: String)

class Counter extends Actor with ActorLogging{
  override def receive: Receive = {
    case Count(path) => {
      val loc = Source.fromFile(path).getLines().length
      sender() ! Report(path, loc)
    }
  }
}
