package com.haiziwang.actor.concurrency.akka.cloc

import akka.actor.{ActorSystem, Props}
import com.typesafe.scalalogging.slf4j.StrictLogging

/**
  * Created by luohao on 2016/9/28.
  */
object ClocApp extends App with StrictLogging {
  val system = ActorSystem("cloc")
  val collector = system.actorOf(Props[Collector],"collector")
  collector ! Collect(".")
  system.terminate()
}
