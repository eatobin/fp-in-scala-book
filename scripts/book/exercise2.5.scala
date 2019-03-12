// Exercise 2.5: Implement `compose`
def compose[A, B, C](f: B => C, g: A => B): A => C =
  a => f(g(a))

def composeTest(): Int = {
  val a: Int = 8
  val result = compose((b: Int) => b * 10, (a: Int) => a + 1)
  val next = result(a)
  next
}

composeTest()

compose((b: Int) => b * 10, (a: Int) => a + 5)(3)

def f(b: Int): Int = b / 2
def g(a: Int): Int = a + 2

assert(compose(f, g)(0) != compose(g, f)(0))
assert(compose(f, g)(2) == 2)
assert(compose(g, f)(2) == 3)

// scala> :load ./scripts/book/exercise2.5.scala
