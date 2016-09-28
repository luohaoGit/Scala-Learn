package com.haiziwang.actor.collection

/**
  * Created by luohao on 2016/9/28.
  */
object collection extends App {
  //dup(List(1,2,3)) should be List(1,1,2,2,3,3)
//  def dup[A]: List[A] => List[A] = ???

  //dup函数返回一个函数List[A] => List[A]
  def dup[A]: List[A] => List[A] = _.map(x => List(x,x)).flatten

  println(dup(List(1,2,3)))
}
