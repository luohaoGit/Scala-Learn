package com.haiziwang

import akka.actor.{ActorSystem, Props}
import com.haiziwang.actor.{StartProcessFileMsg, WordCounterActor}

/**
  * Created by luohao on 2016/9/22.
  */
object Sample extends App {
  import akka.util.Timeout
  import scala.concurrent.duration._
  import akka.pattern.ask
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