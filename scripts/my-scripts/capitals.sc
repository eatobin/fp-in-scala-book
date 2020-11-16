val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")
println("capitals.get( \"France\" ) : " + capitals.get("France")) // capitals.get( "France" ) : Some(Paris)
println("capitals.get( \"India\" ) : " + capitals.get("India")) // capitals.get( "India" ) : None

def show(x: Option[String]): String = x match {
  case Some(s) => s
  case None => "?"
}

println()
println("show(capitals.get( \"Japan\")) : " +
  show(capitals.get("Japan"))) // show(capitals.get( "Japan")) : Tokyo
println("show(capitals.get( \"India\")) : " +
  show(capitals.get("India"))) // show(capitals.get( "India")) : ?

// scala> :load ./scripts/my-scripts/capitals.scala
