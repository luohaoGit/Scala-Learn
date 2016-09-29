package com.haiziwang.lang.partialFunction

/**
  * Created by luohao on 2016/9/29.
  */
object PartialFunctionTest extends App{

  val wordFrequencies = ("habitual", 6) :: ("and", 56) :: ("consuetudinary", 2) ::
    ("additionally", 27) :: ("homely", 5) :: ("society", 13) :: Nil

  //定义偏函数
  //模式匹配型的匿名函数就是偏函数
  val pf: PartialFunction[(String, Int), String] = {
    case (word, freq) if freq > 3 && freq < 25 => word
  }

  print(wordFrequencies.collect(pf));

}
