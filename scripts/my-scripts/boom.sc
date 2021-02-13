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
