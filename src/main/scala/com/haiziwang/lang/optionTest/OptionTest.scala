package com.haiziwang.lang.optionTest

/**
  * Created by luohao on 2016/9/29.
  */
object OptionTest extends App{

  val user = User(2, "Johanna", "Doe", 30, None)
  println("Gender: " + user.gender.getOrElse("not specified")) // will print "not specified"

  val gender = user.gender match {
    case Some(gender) => gender
    case None => "not specified!"
  }
  println("Gender: " + gender)

  UserRepository.findById(2).foreach(user => println(user.firstName))
  println(UserRepository.findById(1).map(_.age))
  println(UserRepository.findById(1).map(_.gender))
  println(UserRepository.findById(1).flatMap(_.gender)) // gender is Some("male")
  println(UserRepository.findById(2).flatMap(_.gender)) // gender is None
  println(UserRepository.findById(3).flatMap(_.gender)) // gender is None

  val names: List[Option[String]] = List(Some("Johanna"), None, Some("Daniel"))
  println(names.map(_.map(_.toUpperCase))) // List(Some("JOHANNA"), None, Some("DANIEL"))
  println(names.flatMap(xs => xs.map(_.toUpperCase))) // List("JOHANNA", "DANIEL")

  //过滤 Option
  println(UserRepository.findById(1).filter(_.age > 30)) // None, because age is <= 30
  println(UserRepository.findById(2).filter(_.age > 30)) // Some(user), because age is > 30
  println(UserRepository.findById(3).filter(_.age > 30)) // None, because user is already None

  //for with option
  val g = for {
    user <- UserRepository.findAll
    gender <- user.gender
  } yield gender
  println(g)

  val g1 = for {
    User(_, _, _, _, Some(gender)) <- UserRepository.findAll
  } yield gender
  println(g1)

  //链接 Option
  case class Resource(content: String)
  val resourceFromConfigDir: Option[Resource] = None
  val resourceFromClasspath: Option[Resource] = Some(Resource("I was found on the classpath"))
  val resource = resourceFromConfigDir orElse resourceFromClasspath
}

case class User(
                 id: Int,
                 firstName: String,
                 lastName: String,
                 age: Int,
                 gender: Option[String]
               )

object UserRepository {
  private val users = Map(1 -> User(1, "John", "Doe", 32, Some("male")),
    2 -> User(2, "Johanna", "Doe", 30, None))
  def findById(id: Int): Option[User] = users.get(id)
  def findAll = users.values
}