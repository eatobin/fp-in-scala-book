// Polymorphic functions are often so constrained by their type
// that they only have one implementation! Here's an example:
def partial1[A, B, C](a: A, f: (A, B) => C): B => C = {
  (b) => f(a, b)
}

def partial2(a: Int, f: (Int, Int) => Int): Int => Int = {
  (b) => f(a, b)
}

def partial1Test(): Int = {
  val b = 3
  val result = partial1(10, (a: Int, b: Int) => a - b) // a = 10
  val last = result(b)
  last
}

def partial1Test2(): Int = {
  val b = 3
  val result = partial1(10, (a: Int, b: Int) => a + b) // a = 10
  val last = result(b)
  last
}

def partial1Test3(): Int = {
  val b = 9
  val last = partial1(10, (a: Int, b: Int) => a + b)(b) // a = 10
  last
}

def partial2Test(): Int = {
  val b = 30
  val result = partial2(100, (a, b) => a - b) // a = 100
  val last = result(b)
  last
}

println(partial1(10, (a: Int, b: Int) => a - b))
println(partial1Test())
println(partial1Test2())
println(partial1Test3())
println(partial1(10, (a: Int, b: Int) => a + b)(66))
println(partial2Test())

def sum(a: Int, b: Int, c: Int) = a + b + c
val a = sum _
a(11, 22, 33)
val b = sum(100, _: Int, 300)
b(200)

var more = 1
val addMore = (x: Int) => x + more
addMore(10)

// scala> :load ./scripts/book/partial.scala
