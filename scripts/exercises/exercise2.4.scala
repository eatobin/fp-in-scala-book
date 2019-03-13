// Exercise 2.4: Implement `uncurry`
def uncurry[A, B, C](f: A => B => C): (A, B) => C =
  (a, b) => f(a)(b)

def uncurryTest(): Int = {
  val a: Int = 20
  val b = 3
  val result = uncurry((a: Int) => (b: Int) => a + b)
  val next = result(a, b)
  next
}

uncurryTest()

/*
NB: There is a method on the `Function` object in the standard library,
`Function.uncurried` that you can use for uncurrying.

Note that we can go back and forth between the two forms. We can curry
and uncurry and the two forms are in some sense "the same". In FP jargon,
we say that they are _isomorphic_ ("iso" = same; "morphe" = shape, form),
a term we inherit from category theory.
*/

// uncurry is the inverse of curry. Its first argument must be a function taking two (curried) values. uncurry then applies that function to the components of the pair which is the second argument.

uncurry((a: Int) => (b: Int) => a * b)(6, 9)
uncurry((a: String) => (b: String) => a + b)("You solved it, ", "Eric!")

def f(a: Int, b: Int): Int = a + b
def g(a: Int)(b: Int): Int = a + b

assert(g(1)(1) == uncurry(g)(1, 1))
assert(f(1, 1) == uncurry(g)(1, 1))

// scala> :load ./scripts/exercises/exercise2.4.scala
