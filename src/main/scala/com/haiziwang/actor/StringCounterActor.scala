package com.haiziwang.actor

import akka.actor.Actor

/**
  * Created by luohao on 2016/9/22.
  */
class StringCounterActor extends Actor {
  def receive = {
    case ProcessStringMsg(string) => {
      println(string)
      val wordsInLine = string.split(" ").length
      sender ! StringProcessedMsg(wordsInLine)
    }
    case _ => println("Error: message not recognized")
  }
}

/**
  * Case Class默认是不可变的并且可以和模式匹配无缝集成
  */
case class ProcessStringMsg(string: String)
case class StringProcessedMsg(words: Integer)
