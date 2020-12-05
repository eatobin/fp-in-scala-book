import scala.io.Source

val bufferedSource =
  Source.fromFile("day01.txt")
val gasList: List[Int] =
  bufferedSource.getLines.toList.map(s => s.toInt)
bufferedSource.close

def gas(m: Int): Int =
  (m / 3) - 2

val answer: Int = gasList.map(m => gas(m)).sum

println(answer)
