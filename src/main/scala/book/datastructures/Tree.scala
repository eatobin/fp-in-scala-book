package book.datastructures

sealed trait Tree[+A]

case class Leaf[A](value: A) extends Tree[A]

case class Branch[A](left: Tree[A], right: Tree[A]) extends Tree[A]


object Tree {
  def size[A](t: Tree[A]): Int = t match {
    case Leaf(_) => 1
    case Branch(l, r) => 1 + size(l) + size(r)
  }

  def maximum(t: Tree[Int]): Int = t match {
    case Leaf(n) => n
    case Branch(l, r) => maximum(l) max maximum(r)
  }

  def depth[A](t: Tree[A]): Int = t match {
    case Leaf(_) => 0
    case Branch(l, r) => 1 + (depth(l) max depth(r))
  }

  def map[A, B](t: Tree[A])(f: A => B): Tree[B] = t match {
    case Leaf(a) => Leaf(f(a))
    case Branch(l, r) => Branch(map(l)(f), map(r)(f))
  }

  /*
  Like `foldRight` for lists, `fold` receives a "handler" for each of the data
  constructors of the type, and recursively accumulates some value using these handlers.
  As with `foldRight`, `fold(t)(Leaf(_))(Branch(_,_)) == t`, and we can use this
  function to implement just about any recursive function that would otherwise
  be defined by pattern matching.
  */

  def fold[A, B](t: Tree[A])(f: A => B)(g: (B, B) => B): B = t match {
    case Leaf(a) => f(a)
    case Branch(l, r) => g(fold(l)(f)(g), fold(r)(f)(g))
  }

  def sizeViaFold[A](t: Tree[A]): Int =
    fold(t)(a => 1)(1 + _ + _)

  def maximumViaFold(t: Tree[Int]): Int =
    fold(t)(a => a)(_ max _)

  def depthViaFold[A](t: Tree[A]): Int =
    fold(t)(a => 0)((d1, d2) => 1 + (d1 max d2))

  /*
  Note the type annotation required on the expression `Leaf(f(a))`. Without this
  annotation, we get an error like this:

  type mismatch;
    found   : fpinscala.datastructures.Branch[B]
    required: fpinscala.datastructures.Leaf[B]
       fold(t)(a => Leaf(f(a)))(Branch(_,_))
                                      ^

  This error is an unfortunate consequence of Scala using subtyping to encode
  algebraic data types. Without the annotation, the result type of the fold gets
  inferred as `Leaf[B]` and it is then expected that the second argument to `fold`
  will return `Leaf[B]`, which it doesn't (it returns `Branch[B]`). Really, we'd
  prefer Scala to infer `Tree[B]` as the result type in both cases. When working
  with algebraic data types in Scala, it's somewhat common to define helper functions
  that simply call the corresponding data constructors but give the less specific result type:

    def leaf[A](a: A): Tree[A] = Leaf(a)
    def branch[A](l: Tree[A], r: Tree[A]): Tree[A] = Branch(l, r)
  */

  def mapViaFold[A, B](t: Tree[A])(f: A => B): Tree[B] =
    fold(t)(a => Leaf(f(a)): Tree[B])(Branch(_, _))
}

object TreeTester {
  def main(args: Array[String]): Unit = {
    val p = ("Bob", 42)
    println(p)
    println(p._1)
    println(p._2)
    val j = p match {
      case (a, b) => a
    }
    println(j)
    val bottomBranchLeft = Branch(Leaf("a"), Leaf("b"))
    val bottomBranchRight = Branch(Leaf("c"), Leaf("d"))
    val topBranch = Branch(bottomBranchLeft, bottomBranchRight)
    val topBranch2 = Branch(Branch(Leaf("a"), Leaf("b")), Branch(Leaf("c"), Leaf("d")))
    val topBranch3 = Branch(Branch(Leaf(62), Leaf(19)), Branch(Leaf(479), Leaf(8)))
    println(topBranch)
    println("size topBranch: " + Tree.size(topBranch))
    println("size topBranch2: " + Tree.size(topBranch2))
    println("size aBottomBranch: " + Tree.size(bottomBranchLeft))
    println("maximum topBranch3: " + Tree.maximum(topBranch3))
    println("depth topBranch3: " + Tree.depth(topBranch3))
    println("map topBranch3: " + Tree.map(topBranch3)((a) => a + 1))
    println("sizeViaFold topBranch: " + Tree.sizeViaFold(topBranch))
    println("maximumViaFold topBranch3: " + Tree.maximumViaFold(topBranch3))
    println("depthViaFold topBranch3: " + Tree.depthViaFold(topBranch3))
    println("mapViaFold topBranch3: " + Tree.mapViaFold(topBranch3)((a) => a + 11))
  }
}
