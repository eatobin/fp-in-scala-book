// Exercise 1: Write a function to compute the nth fibonacci number
def fib(n: Int): Int = {
  @annotation.tailrec
  def go(a: Int, b: Int, ctr: Int): Int = {
    if (ctr == n) a + b
    else go(b, a + b, ctr + 1)
  }
  go(0, 1, 3)
}

def formatFib(x: Int) = {
  val msg = "Fibonacci position %d is %d"
  msg.format(x, fib(x))
}

println(formatFib(31))
