package com.haiziwang.actor.initdelay

/**
  * Created by luohao on 2016/9/27.
  */
object TestClosure extends App{
  val words = Array("a","ab","abc")
  val count = 10

  val cnt = words.map{word => (word, count)}
  cnt.foreach(println)
}
