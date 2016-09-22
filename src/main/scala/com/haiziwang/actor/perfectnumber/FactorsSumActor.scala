package com.haiziwang.actor.perfectnumber

import akka.actor.Actor

/**
  * Created by luohao on 2016/9/22.
  */
class FactorsSumActor(number: Int) extends Actor{

  def receive = {

    case FactorSumRequest(lower, upper) => {
      println("start from " + lower + "to" + upper)
      val sum = (0 /: (lower to upper)){
        (sum, i) => if(number%i == 0) sum+i else sum
      }
      sender ! FactorSumResponse(sum)
    }

    case _ => println("Error: message not recognized")
  }

}

case class FactorSumRequest(lower: Int, upper: Int)
case class FactorSumResponse(result: Int)