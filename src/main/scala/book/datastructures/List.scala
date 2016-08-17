package book.datastructures

// `List` data type, parameterized on a type, `C`
sealed trait List[+C]

// A `List` data constructor representing the empty list
case object Nil extends List[Nothing]

// Another data constructor, representing nonempty lists. Note that `tail` is another `List[C]`,
// which may be `Nil` or another `Cons`.
case class Cons[+C](head: C, tail: List[C]) extends List[C]

// `List` companion object. Contains functions for creating and working with lists.
object List {

  val s: String = "Hey there again, Eric!" // My test variable for main

  // A function that uses pattern matching to add up a list of integers
  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0 // The sum of the empty list is 0.
    case Cons(h, t) => h + sum(t) // The sum of a list starting with `h` is `h` plus the sum of the rest of the list.
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(h, t) => h * product(t)
  }

  // Variadic function syntax
  def apply[C](l: C*): List[C] = {
    if (l.isEmpty) Nil
    else Cons(l.head, apply(l.tail: _*))
  }

  val z = List(1, 2, 3, 4, 5) match {
    //    case _ => 42
    //    case Cons(h, _) => h
    //    case Cons(_, t) => t
    //    case Nil => 42
    //
    //    case Cons(x, Cons(2, Cons(4, _))) => x // Returns no match - break in seq. 1, 2, 3 works and returns 1
    //    case Cons(1, Cons(x, Cons(3, _))) => x // Returns 2
    //    case Cons(1, Cons(2, Cons(x, _))) => x // Returns 3
    //    case Cons(1, Cons(62, Cons(x, _))) => x // Returns  no match
    //    case Nil => 42 // Returns no match
    //    case Cons(x, Cons(y, Cons(3, Cons(4, _)))) => x + y // Returns 3
    //    case Cons(1, Cons(2, Cons(x, Cons(y, _)))) => x + y // Returns 7
    //    case Cons(2, Cons(3, Cons(x, Cons(y, _)))) => x + y // Returns no match
    //    case Cons(2, Cons(x, Cons(y, _))) => x + y //Returns no match
    //    case Cons(h, t) => h + sum(t) // Returns 15
    //    case Cons(1, Cons(2, Cons(h, t))) => h + sum(t) // Returns 12
    case _ => 101
  }

  def append[C](a1: List[C], a2: List[C]): List[C] = a1 match {
    case Nil => a2
    case Cons(h, t) => Cons(h, append(t, a2))
  }

  // Utility functions
  def foldRight[C, R](l: List[C], r: R)(f: (C, R) => R): R = l match {
    case Nil => r
    case Cons(h, t) => f(h, foldRight(t, r)(f))
  }

  def sum2(ns: List[Int]): Int = {
    foldRight(ns, 0)((x, y) => x + y)
  }

  def product2(ns: List[Double]): Double = {
    foldRight(ns, 1.0)(_ * _) // `_ * _` is more concise notation for `(x,y) => x * y`; see sidebar
  }

  def tail[C](l: List[C]): List[C] = l match {
    case Nil => sys.error("tail: tail of empty list")
    case Cons(_, t) => t
  }

  def setHead[C](l: List[C], h: C): List[C] = l match {
    case Nil => sys.error("setHead: set head of empty list")
    case Cons(_, t) => Cons(h, t)
  }

  @annotation.tailrec
  def drop[C](l: List[C], n: Int): List[C] = {
    if (n <= 0) l
    else l match {
      case Nil => Nil
      case Cons(_, t) => drop(t, n - 1)
    }
  }

  /*
  Somewhat overkill, but to illustrate the feature we're using a _pattern guard_,
  to only match a `Cons` whose head satisfies our predicate, `f`.
  The syntax is to add `if <cond>` after the pattern, before the `=>`,
  where `<cond>` can use any of the variables introduced by the pattern.
  */

  /*
  Hello, Perhaps my reading comprehension is bad on this problem but consider
  exercise 3.5 "Implement dropWhile, which removes elements from the List prefix
  as long as they match a predicate." When I read that exercise problem originally,
  it seemed that you could have a List that has items that alternate between
  matching the predicate and not matching the predicate. Perhaps you would walk
  the entire list and only selectively drop the ones that match the predicate?
  However, the solution on github stops iterating through the list at the first
  non-matching element. If this is the intent it might be helpful to add that as
  a requirement.
   */
  @annotation.tailrec
  def dropWhile[C](l: List[C], f: C => Boolean): List[C] = {
    l match {
      case Cons(h, t) if f(h) => dropWhile(t, f)
      case _ => l
    }
  }

  def init[C](l: List[C]): List[C] = {
    l match {
      case Nil => sys.error("init of empty list")
      case Cons(_, Nil) => Nil
      case Cons(h, t) => Cons(h, init(t))
    }
  }

  /*
  #7
  No, this is not possible! The reason is because _before_ we ever call our function,
  `f`, we evaluate its argument, which in the case of `foldRight` means traversing
  the list all the way to the end. We need _non-strict_ evaluation to support early
  termination---we discuss this in chapter 5.
  */

  /*
  #8
  We get back the original list! Why is that? As we mentioned earlier, one way of
  thinking about what `foldRight` "does" is it replaces the `Nil` constructor of
  the list with the `z` argument, and it replaces the `Cons` constructor with the
  given function, `f`. If we just supply `Nil` for `z` and `Cons` for `f`, then
  we get back the input list.

  foldRight(Cons(1, Cons(2, Cons(3, Nil))), Nil:List[Int])(Cons(_,_))
  Cons(1, foldRight(Cons(2, Cons(3, Nil)), Nil:List[Int])(Cons(_,_)))
  Cons(1, Cons(2, foldRight(Cons(3, Nil), Nil:List[Int])(Cons(_,_))))
  Cons(1, Cons(2, Cons(3, foldRight(Nil, Nil:List[Int])(Cons(_,_)))))
  Cons(1, Cons(2, Cons(3, Nil)))
  */

  def length[C](l: List[C]): Int = foldRight(l, 0)((_, r) => r + 1)

  def sumLeft(l: List[Int]): Int = foldLeft(l, 0)((r, c) => r + c)

  def productLeft(l: List[Double]): Double = foldLeft(l, 1.0)((r, c) => r.*(c))

  //  def productLeft(l: List[Double]) = foldLeft(l, 1.0)(_ * _)

  def lengthLeft[C](l: List[C]): Int = foldLeft(l, 0)((r, _) => r + 1)

  def revLeft[C](l: List[C]): List[C] = foldLeft(l, List[C]())((r, c) => Cons(c, r))

  @annotation.tailrec
  def foldLeft[R, C](l: List[C], r: R)(f: (R, C) => R): R = l match {
    case Nil => r
    case Cons(h, t) => foldLeft(t, f(r, h))(f)
  }

  def appendRight[C](l: List[C], r: List[C]): List[C] =
    foldRight(l, r)(Cons(_, _))

  def plusOne(l: List[Int]): List[Int] = l match {
    case Nil => sys.error("plusOne of empty list")
    case Cons(h, Nil) => Cons(h + 1, Nil)
    case Cons(h, t) => Cons(h + 1, plusOne(t))
  }

  def plusOneRight(l: List[Int]): List[Int] =
    foldRight(l, List[Int]())((c, r) => Cons(c + 1, r))

  def plusOneLeft(l: List[Int]): List[Int] =
    foldLeft(l, List[Int]())((r, c) => Cons(c + 1, r))

  def toMyString(l: List[Double]): List[String] =
    foldRight(l, Nil: List[String])((c, r) => Cons(c.toString, r))

  def map[C, R](l: List[C])(f: C => R): List[R] =
    foldRight(l, Nil: List[R])((c, r) => Cons(f(c), r))

  def filter[C](l: List[C])(f: C => Boolean): List[C] =
    foldRight(l, List[C]())((c, r) => if (f(c)) Cons(c, r) else r)

  /*
  To match on multiple values, we can put the values into a pair
  and match on the pair, as shown next, and the same syntax extends
  to matching on N values (see sidebar "Pairs and tuples in Scala"
  for more about pair and tuple objects). You can also (somewhat
  less conveniently, but a bit more efficiently) nest pattern matches:
  on the right hand side of the `=>`, simply begin another `match` expression.
  The inner `match` will have access to all the variables introduced in the outer `match`.

  The discussion about stack usage from the explanation of `map` also applies here.
  */

  def addPairwise(a: List[Int], b: List[Int]): List[Int] = (a, b) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (Cons(h1, t1), Cons(h2, t2)) => Cons(h1 + h2, addPairwise(t1, t2))
  }

  def zipWith[A, B, C](a: List[A], b: List[B])(f: (A, B) => C): List[C] = (a, b) match {
    case (Nil, _) => Nil
    case (_, Nil) => Nil
    case (Cons(h1, t1), Cons(h2, t2)) => Cons(f(h1, h2), zipWith(t1, t2)(f))
  }
}

object ListTester {
  def main(args: Array[String]): Unit = {
    println("Hi Ericky!")
    println(List.s)
    println()
    println(List.z)
    val zz = List(1, 1, 3, 1, 4, 5, 60)
    val zzz = List(1.0, 2.0, 3.0, 4.0)
    //    val zz = List('C', 'B', 'c', 'd')
    val zzx = List("One", "Two", "Three", "One", "Four", "Five")
    //    val zz = List()
    println(List.tail(zz))
    println(List.setHead(zz, "NewZero"))
    println("drop: " + List.drop(zz, 1))
    println("dropWhile: " + List.dropWhile(zz, (n: Int) => n == 1))
    List(100, 200, 300, 400, 500) match {
      case Nil =>
      case Cons(h, t) => print(h + " : " + t)
    }
    println()
    println("append: " + List.append(List(1, 1, 3, 1, 4, 5), List(10, 20, 30, 40, 50)))
    println("append2: " + List.append(List(), List(10, 20, 30, 40, 50)))
    println("init: " + List.init(zz))
    println("foldRight: " + List.foldRight(List("one", "two", "three"), "zero")((a, b) => a ++ b)) // foldRight: onetwothreezero
    //    println(List.foldRight(List(), "zero")((a, b) => a ++ b)) Won't compile - see foldLeft
    println("foldLeft: " + List.foldLeft(List("one", "two", "three"), "zero")((b, a) => b ++ a)) // foldLeft: zeroonetwothree
    println("foldLeft: " + List.foldLeft(List(), "zero")((b, a) => b ++ a)) // foldLeft: zero
    println("sum: " + List.sum(zz))
    println("sum2: " + List.sum2(zz))
    println("length: " + List.length(zz))
    println("sumLeft: " + List.sumLeft(zz))
    println("productLeft: " + List.productLeft(zzz))
    println("lengthLeft: " + List.lengthLeft(zz))
    println("revLeft: " + List.revLeft(zz))
    println("appendRight: " + List.appendRight(List(1, 2, 3, 4, 5), List(10, 20, 30, 40, 50)))
    println("plusOne: " + List.plusOne(zz))
    println("plusOneRight: " + List.plusOneRight(zz))
    println("plusOneLeft: " + List.plusOneLeft(zz))
    println("plusOneLeftEmpty: " + List.plusOneLeft(List()))
    println("toMyString: " + List.toMyString(zzz))
    println("map: " + List.map(zzx)((c) => c.toUpperCase))
    println("filter: " + List.filter(zz)((c) => c % 2 == 0))
    println("addPairWise: " + List.addPairwise(List(1, 2, 3), List(10, 20, 30)))
    println("zipWith: " + List.zipWith(List(1, 2, 3), List(10, 20, 30))((a, b) => a + b))
    println("zipWith: " + List.zipWith(List(1, 2, 3, 4), List("Dog", "Cats", "Mice"))((a, b) => a + "-" + b))
  }
}
