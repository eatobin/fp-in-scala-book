val w: String = "High"
val ws: List[String] = List("do", "ray", "me", "bermuda")
val ns: List[Int] = List(1, 2, 3, 4)
println(w.length)
def howLong(word: String): Int = word.length
def howTwice(int: Int): Int = int * 2
println(howLong(w))
def myMap(f: String => Int, list: List[String]): List[Int] = {
  for (w <- list) yield {
    f(w)
  }
}
val lengths: List[Int] = myMap(howLong, ws)
println(lengths)
def myBetterMap[A, B](f: A => B, list: List[A]): List[B] = for (w <- list) yield f(w)
val betterLengths: List[Int] = myBetterMap(howLong, ws)
println(betterLengths)
println(myBetterMap(howTwice, ns))
