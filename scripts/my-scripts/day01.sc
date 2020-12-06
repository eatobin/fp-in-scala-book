import scala.annotation.tailrec
import scala.io.Source
import scala.math.max

val bufferedSource =
  Source.fromFile("day01.txt")
val gasList: Seq[Int] =
  bufferedSource.getLines.toSeq.map(s => s.toInt)
bufferedSource.close

// part a
def gas(m: Int): Int =
  (m / 3) - 2

val answer: Int = gasList.map(m => gas(m)).sum

println(answer)

// 3337766

// part b
def gasPlus(m: Int): Int = {
  @tailrec
  def gasAccumulator(m: Int, accum: Int): Int = {
    val newGas: Int = max((m / 3) - 2, 0)
    if (newGas > 0) {
      gasAccumulator(newGas, accum + newGas)
    } else {
      accum
    }
  }

  gasAccumulator(m, 0)
}

val answer2: Int = gasList.map(m => gasPlus(m)).sum

println(answer2)

// 5003788
