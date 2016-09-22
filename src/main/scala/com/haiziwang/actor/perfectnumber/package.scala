package com.haiziwang.actor

import akka.actor.Props
import com.haiziwang.actor.perfectnumber.{FactorSumRequest, FactorsSumActor}

/**
  * Created by luohao on 2016/9/22.
  */
object test{

  def main(args: Array[String]): Unit = {

    val candidate = 33550300
    val size = 1000000
    val pages = (candidate.toDouble/size).ceil.toInt

    for(i <-0 until pages){
      val lower = i*size + 1
      val upper = candidate min(i+1)*size

      context.actorOf(Props[FactorsSumActor]) ! FactorSumRequest(lower, upper)
    }

  }

}
