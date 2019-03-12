// Exercise 3: Implement `curry`.
// Note that `=>` associates to the right, so we could
// write the return type as `A => B => C`
def curry[A, B, C](f: (A, B) => C): A => B => C =
  a => b => f(a, b)

def curryTest(): Int = {
  val a: Int = 2
  val b: Int = 3
  val result = curry((a: Int, b: Int) => a + b)
  val next = result(a)
  val last = next(b)
  last
}

curryTest()

println(curry((a: Int, b: Int) => a + b))
println(curry((a: Int, b: Int) => a - b))

// curry's first argument must be a function which accepts a pair. It applies that function to its next two arguments.

println(curry((a: Int, b: Int) => a - b)(22)(7)) // BEST
println(curry((a: String, b: String) => a + b)("Here's word 1 and ")("here's word 2."))

def f(a: Int, b: Int): Int = a + b
def g(a: Int)(b: Int): Int = a + b

assert(curry(f)(1)(1) == f(1, 1))
assert(curry(f)(1)(1) == g(1)(1))

// scala> :load ./scripts/book/exercise3.scala
