def capitalize(word: String): String = {
  @annotation.tailrec
  def go(word: String, accum: String): String = {
    if (word.trim.isEmpty)
      accum.reverse
    else
      go(word.tail, word.head.toUpper.toString ++ accum)
  }

  go(word, "")
}

println(capitalize("dogs"))

// scala> :load ./scripts/my-scripts/capitalize.scala
