package com.haiziwang.actor

/**
  * Created by luohao on 2016/9/28.
  */
package object compose {

  def split: String => List[String] = s => s.split(",").toList

}
