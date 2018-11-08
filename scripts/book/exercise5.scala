// Exercise 5: Implement `compose`
def compose[A, B, C](f: B => C, g: A => B): A => C = a => f(g(a))

def composeTest(): Int = {
  val a: Int = 8
  val result = compose((b: Int) => b * 10, (a: Int) => a + 1)
  val next = result(a)
  next
}

println(compose((b: Int) => b * 1, (a: Int) => a * 1))
println(composeTest())
println(compose((b: Int) => b * 10, (a: Int) => a + 5)(3))

// scala> :load ./scripts/book/exercise5.scala
