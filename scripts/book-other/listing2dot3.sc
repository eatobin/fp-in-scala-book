// From the book pg. 23 - Listing 2.3
def findFirst(ss: Array[String], key: String): Int = {
  @annotation.tailrec
  def loop(n: Int): Int =
    if (n >= ss.length) -1
    else if (ss(n) == key) n
    else loop(n + 1)

  loop(0)
}

println(findFirst(Array("Apple", "Banana", "Orange"), "Banana"))
