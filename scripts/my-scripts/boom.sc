import scala.annotation.tailrec

@tailrec
def boom(x: Int): Int = {
  if (x == 0) 0
  else boom(x - 1)
}
boom(3)

def add(a: Int, b: Int): Int = a + b
val add3: Int => Int = add(3, _)
val sum = add3(5)
println(sum)

def len(l: List[_]): Int =
  l match {
    case Nil => 0
    case _ :: tail => len(tail) + 1
  }

println(len(List(1, 2, 3)))
println(len(List("cat", "dog")))

def lenR(l: List[_]): Int = {
  @tailrec
  def loop(cursor: List[_], count: Int): Int =
    cursor match {
      case Nil => count
      case _ :: tail => loop(cursor = tail, count = count + 1)
    }

  loop(l, 0)
}

println(lenR(List(1, 2, 3, 4)))
println(len(List("cat", "dog")))
println(lenR(List.fill(100001)(1)))
