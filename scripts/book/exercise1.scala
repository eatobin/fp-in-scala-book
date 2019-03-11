// Exercise 1: Write a function to compute the nth fibonacci number
def fib(n: Int): Int = {
  @annotation.tailrec
  def go(a: Int, b: Int, ctr: Int): Int = {
    if (ctr == n) a + b
    else go(b, a + b, ctr + 1)
  }

  go(0, 1, 3)
}

assert(fib(3) == 1)
assert(fib(4) == 2)
assert(fib(5) == 3)

def fib_2(n: Int): Int = {
  @annotation.tailrec
  def loop(n: Int, prev: Int, cur: Int): Int =
    if (n <= 0) prev
    else loop(n - 1, cur, prev + cur)

  loop(n, 0, 1)
}

assert(fib_2(0) == 0)
assert(fib_2(1) == 1)
assert(fib_2(5) == 5)

// scala> :load ./scripts/book/exercise1.scala
