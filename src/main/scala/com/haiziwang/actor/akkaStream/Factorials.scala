package com.haiziwang.actor.akkaStream

import java.nio.file.Paths

import akka.NotUsed
import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl._
import akka.util.ByteString

import scala.concurrent.duration._
import scala.language.postfixOps
import scala.concurrent.{Await, Future}

/**
  * Created by luohao on 2016/9/27.
  */
object Factorials extends App {

  implicit val system = ActorSystem("QuickStart")
  implicit val materializer = ActorMaterializer()

  val source: Source[Int, NotUsed] = Source(1 to 100)
  source.runForeach(println)

  val factorials = source.scan(BigInt(1))((acc, next) => acc * next)

  def lineSink(fileName: String): Sink[String, Future[IOResult]] = {
    Flow[String]
      .map(num => ByteString(s"$num\n"))
      .toMat(FileIO.toPath(Paths.get(fileName)))(Keep.right)
  }

  Await.ready(factorials.map(_.toString)
    .runWith(lineSink("factorials2.txt")), 3 second)

  system.terminate()
}
