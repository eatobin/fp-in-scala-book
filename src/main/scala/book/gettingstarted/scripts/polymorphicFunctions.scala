// From the book pg. 23 - Listing 2.4
def findFirst[A](as: Array[A], p: A => Boolean): Int = {
  @annotation.tailrec
  def loop(n: Int): Int = {
    if (n >= as.length) -1
    else if (p(as(n))) n
    else loop(n + 1)
  }
  loop(0)
}

println(findFirst(Array("Apple", "Banana", "Orange"), (s: String) => s.equals("Orange")))
println(findFirst(Array(1, 2, 3, 4), (n: Int) => n == 2))
println(findFirst(Array(1, 2, 3, 4), (n: Int) => n > 3))
println(findFirst(Array(1, 2, 3, 4), (n: Int) => n > 2))
