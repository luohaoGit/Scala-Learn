package com.haiziwang.lang.forYield

import com.typesafe.scalalogging.slf4j.StrictLogging

/**
  * Created by luohao on 2016/9/29.
  */
object ForTest extends App with StrictLogging{

  def gameResults(): Seq[(String, Int)] =
    ("Daniel", 3500) :: ("Melissa", 13000) :: ("John", 7000) :: Nil
  def hallOfFame = for {
    result <- gameResults()
    (name, score) = result
    if (score > 5000)
  } yield name

  print(hallOfFame)

}
