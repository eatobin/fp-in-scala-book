val numbers = List(5, 4, 8, 6, 2)
val it = numbers.fold(0) { (a, i) =>
  a + i
}
println(it) // 25

val it1 = numbers.foldRight(0) { (a, i) =>
  a + i
}
println(it1) // 25

val it2 = numbers.foldLeft(0) { (a, i) =>
  a + i
}
println(it2) // 25

// scala> :load ./scripts/my-scripts/it.scala
