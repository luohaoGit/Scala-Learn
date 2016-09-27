package com.haiziwang.actor.initdelay

  /**
  * Created by luohao on 2016/9/27.
  */
object TestRef extends App {
  //对应上面map里面那个匿名函数
  val c = Class.forName("TestClosure$$anonfun$1")
  val meod = c.getDeclaredMethod("apply", classOf[String])
  val res = meod.invoke(c.newInstance(), "zhang")

  // (zhang,0) 并不是 (zhang,10)，说明外层object的count并没有被赋值
  println(res)
}