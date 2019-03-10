// Exercise 2: Implement a polymorphic function to check whether
// an `Array[A]` is sorted
def isSorted[A](as: Array[A], gt: (A, A) => Boolean): Boolean = {
  @annotation.tailrec
  def loop(n: Int): Boolean = {
    if (n >= as.length - 1) true
    else if (gt(as(n), as(n + 1))) false
    else loop(n + 1)
  }
  loop(0)
}

println(isSorted(Array(1, 2, 3, 4), (a: Int, b: Int) => a > b))
println(isSorted(Array(1, 2, 34, 4), (a: Int, b: Int) => a > b))
println(isSorted(Array(10, 20, 30, 4), (a: Int, b: Int) => a > b))

println(isSorted(Array("cat", "mouse", "zebra"), (a: String, b: String) => a > b))
println(isSorted(Array("cat", "mouse", "zebra", "horse"), (a: String, b: String) => a > b))

// :load ./scripts/book/exercise2.scala
