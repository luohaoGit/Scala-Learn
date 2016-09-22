package com.haiziwang.actor.countwords

import akka.actor.{ActorSystem, Props}

/**
  * Created by luohao on 2016/9/22.
  */
object Test extends App {
  import akka.pattern.ask
  import akka.util.Timeout

  import scala.concurrent.duration._
  override def main(args: Array[String]) {
    val system = ActorSystem("System")
    val actor = system.actorOf(Props(new WordCounterActor("d:/fc.txt")))
    implicit val timeout = Timeout(25 seconds)
    val future = actor ? StartProcessFileMsg()
    implicit val ec = akka.dispatch.ExecutionContexts.global
    future.map { result =>
      println("Total number of words " + result)
      system.shutdown
    }
  }
}