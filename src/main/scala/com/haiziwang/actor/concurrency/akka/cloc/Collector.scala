package com.haiziwang.actor.concurrency.akka.cloc

import java.io.File

import akka.actor.{Actor, ActorLogging, Props}

/**
  * Created by luohao on 2016/9/28.
  */
case class Collect(directory: String)
case class Report(filePath: String, loc: Int)

class Collector extends Actor with ActorLogging {

  override def receive: Receive = {
    case Collect(dir) => {
      val files = new File(dir).listFiles().filter(_.isFile)
      files.foreach(file => {
        context.actorOf(Props[Counter],file.getName) ! Count(file.getPath)
      })
    }
    case Report(filePath, loc) => log.info(s"$filePath has $loc lines of code")
  }
}

