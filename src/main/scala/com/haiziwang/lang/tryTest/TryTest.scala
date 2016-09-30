package com.haiziwang.lang.tryTest

import java.io.InputStream
import java.net.URL

import scala.util.Try

/**
  * Created by luohao on 2016/9/30.
  */
object TryTest extends App{

  val baidu = "http://www.baidu.com"

  def parseURL(url: String): Try[URL] = Try(new URL(url))

  //val url = parseURL(Console.readLine("URL: ")) getOrElse new URL(baidu)

  println(parseURL(baidu).map(_.getProtocol))

  println(parseURL("garbage").map(_.getProtocol))

  //def inputStreamForURL(url: String): Try[Try[Try[InputStream]]] = parseURL(url).map { u => Try(u.openConnection()).map(conn => Try(conn.getInputStream))}

  def inputStreamForURL(url: String): Try[InputStream] = parseURL(url).flatMap { u => Try(u.openConnection()).flatMap(conn => Try(conn.getInputStream)) }

  def parseHttpURL(url: String) = parseURL(url).filter(_.getProtocol == "http")
  println(parseHttpURL("http://apache.openmirror.de")) // results in a Success[URL]
  println(parseHttpURL("ftp://mirror.netcologne.de/apache.org")) // results in a Failure[URL]

  parseHttpURL("http://danielwestheide.com").foreach(println) //当 Try 是 Failure 时， foreach 不会执行，返回 Unit 类型def getURLContent(url: String): Try[Iterator[String]] =

  import scala.io.Source
  def getURLContent(url: String): Try[Iterator[String]] =
    for {
      url <- parseURL(url)
      connection <- Try(url.openConnection())
      is <- Try(connection.getInputStream)
      source = Source.fromInputStream(is)//可以用source = Source.fromURL(url)代替
    } yield source.getLines()
  println(getURLContent(baidu))

  import scala.util.{Failure, Success}
  getURLContent(baidu) match {
    case Success(lines) => lines.foreach(println)
    case Failure(ex) => println(s"Problem rendering URL content: ${ex.getMessage}")
  }


  import java.io.FileNotFoundException
  import java.net.MalformedURLException
  val content = getURLContent("garbage") recover {
    case e: FileNotFoundException => Iterator("Requested page does not exist")
    case e: MalformedURLException => Iterator("Please make sure to enter a valid URL")
    case _ => Iterator("An unexpected error has occurred. We are so sorry!")
  }
  content.get.foreach(println)

}
