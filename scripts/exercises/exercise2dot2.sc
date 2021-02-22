// Exercise 2.2: Implement a polymorphic function to check whether
// an `Array[A]` is sorted
def isSorted[A](as: Array[A], ordering: (A, A) => Boolean): Boolean = {
  @annotation.tailrec
  def go(n: Int): Boolean =
    if (n >= as.length - 1) true
    else if (ordering(as(n), as(n + 1))) false
    else go(n + 1)

  go(0)
}

assert(isSorted(Array(1, 3, 5, 7), (x: Int, y: Int) => x > y))
assert(!isSorted(Array(7, 5, 1, 3), (x: Int, y: Int) => x < y))
assert(isSorted(Array("Scala", "Exercises"), (x: String, y: String) => x.length > y.length))

assert(!isSorted(Array(1, 2, 34, 4), (a: Int, b: Int) => a > b))
assert(isSorted(Array("cat", "mouse", "zebra"), (a: String, b: String) => a > b))
assert(!isSorted(Array("cat", "mouse", "zebra", "horse"), (a: String, b: String) => a > b))
assert(isSorted(Array(7, 5, 3, 1), (x: Int, y: Int) => x < y))
