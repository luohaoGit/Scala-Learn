package com.haiziwang.actor.concurrency.akka

/**
  * Created by luohao on 2016/9/28.
  */
object Concurrency {
  def sum(xs: List[Int]): Int = xs match {
    case Nil => 0
    case (x :: Nil) => x
    case _ => {
      val (l,r) = xs.splitAt(xs.length / 2)
      sum(l) + sum(r)
    }
  }
}
