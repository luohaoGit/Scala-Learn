package com.haiziwang.actor

/**
  * Created by luohao on 2016/9/21.
  */
object Test {

  def sumOfFactors(number:Int) = {
    (0 /: (1 to number)){
      (sum, i) => if(number%i == 0) sum+i else sum
    }
  }

  def isPerfect(candidate:Int) = 2*candidate == sumOfFactors(candidate)

  def main(args: Array[String]): Unit = {
    println("6 is perfect? " + isPerfect(6))
    println("33550336 is perfect? " + isPerfect(33550336))
    println("33550337 is perfect? " + isPerfect(33550337))
  }

}
