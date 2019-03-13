val numbers99 = Map(1 -> "One", 2 -> "Two")
println("numbers99.get(1) : " + numbers99.get(1)) // numbers99.get(1) : Some(One)
println("numbers99.get(9) : " + numbers99.get(9)) // numbers99.get(9) : None

def show2(x: Option[String]) = x match {
  case Some(s) => s
  case None => "?"
}

println()
println("show(numbers99.get(2)) : " +
  show2(numbers99.get(2))) // show(numbers99.get(1)) : Two
println("show(numbers99.get(9)) : " +
  show2(numbers99.get(9))) // show(numbers99.get(9)) : ?

val myList: List[String] = "My" :: ("first" :: ("list" :: Nil))
println()
println(myList.map(c => c.toUpperCase))

val x = 1
def constFun1[X](x: X): Int = 1 // constFun1("jjjj")
def identFun[X](x: X): X = x

println(constFun1("jjjj"))
println(identFun("jjjj"))

// scala> :load ./scripts/my-scripts/map_test.scala
