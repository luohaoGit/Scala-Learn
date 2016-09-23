package com.haiziwang.actor

import akka.actor.{ActorSystem, Props}
import akka.util.Timeout
import com.haiziwang.actor.perfectnumber.{FactorSumRequest, FactorSumResponse, FactorsSumActor}

/**
  * Created by luohao on 2016/9/22.
  */
object test{

  def main(args: Array[String]): Unit = {
    import akka.pattern.ask
    import scala.concurrent.duration._
    val candidate = 33550300
    val size = 1000000
    val pages = (candidate.toDouble/size).ceil.toInt
    var processed = 0;
    var factorsSummer = 0;
    val system = ActorSystem("System")

    for(i <-0 until pages){
      val lower = i*size + 1
      val upper = candidate min(i+1)*size

      val actor = system.actorOf(Props(new FactorsSumActor(candidate)));

      implicit val timeout = Timeout(25 seconds)
      val future = actor ? FactorSumRequest(lower, upper)
      implicit val ec = akka.dispatch.ExecutionContexts.global
      future.map { result =>
        println("factorsSummer " + result)
        processed += 1
        //factorsSummer += result
        if(processed == pages)
          system.shutdown
      }
    }
  }

}
