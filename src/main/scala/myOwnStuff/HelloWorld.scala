package myOwnStuff

//import scala.collection.immutable.TreeMap

//object HelloWorld {
//  def main(args: Array[String]) {
//    println("Hello, world - in Scala!")
//  }
//}

//object HelloWorld extends App {
//  println("Hello, world - in Scala - as an 'App'!")
//}

//object HelloWorld extends App {
//  var islands: Int = 30
//  var capital = TreeMap("US" -> "Washington", "France" -> "Paris")
//  capital += ("Japan" -> "Tokyo")
//  println(capital)
//  println("The capital of Japan is: " + capital("Japan") + "!!")
//  println("It has " + islands + " islands.")
//}

import java.text.DateFormat._
import java.util.{Date, Locale}

object HelloWorld {
  def main(args: Array[String]) {
    val now = new Date
    val df = getDateInstance(LONG, Locale.FRANCE)
    println("Hi Eric! - " + (df format now))
  }
}
