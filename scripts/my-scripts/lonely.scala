/*
  foldLeft() method, available for all collections in Scala, allows to run a given
  2-argument function against consecutive elements of that collection, where the
  result of that function is passed as the first argument in the next invocation.
  Second argument is always the current item in the collection.
 */

/*
  def foldLeft[B](z: B)(f: (B, A) ⇒ B): B
  Applies a binary operator to a start value and all elements of this sequence, going left to right.

  foldLeft
  scala> numbers.foldLeft(0)((m: Int, n: Int) => m + n)
  res0: Int = 55
  0 is the starting value (Remember that numbers is a List[Int]), and m (param on LEFT!)
  acts as an accumulator.

  Seen visually:

  scala> numbers.foldLeft(0) { (m: Int, n: Int) => println("m: " + m + " n: " + n); m + n }
  m: 0 n: 1
  m: 1 n: 2
  m: 3 n: 3
  m: 6 n: 4
  m: 10 n: 5
  m: 15 n: 6
  m: 21 n: 7
  m: 28 n: 8
  m: 36 n: 9
  m: 45 n: 10
  res0: Int = 55

  def foldRight[B](z: B)(op: (A, B) ⇒ B): B
  Applies a binary operator to all elements of this list and a start value, going right to left.

  foldRight
  Is the same as foldLeft except it runs in the opposite direction. 0 is the starting value
  (Remember that numbers is a List[Int]), and n(param on RIGHT!) acts as an accumulator.

  scala> numbers.foldRight(0) { (m: Int, n: Int) => println("m: " + m + " n: " + n); m + n }
  m: 10 n: 0
  m: 9 n: 10
  m: 8 n: 19
  m: 7 n: 27
  m: 6 n: 34
  m: 5 n: 40
  m: 4 n: 45
  m: 3 n: 49
  m: 2 n: 52
  m: 1 n: 54
  res0: Int = 55
 */

val words = List("hey", "there", "lonely", "girl")

println()
println("foldRight(m, n) m ++ n")
val it633 = words.foldRight("Okay") { (m, n) => println("m: " + m + " n: " + n); m ++ n }
println(it633)
/*
foldRight(m, n) m ++ n
m: girl n: Okay
m: lonely n: girlOkay
m: there n: lonelygirlOkay
m: hey n: therelonelygirlOkay
heytherelonelygirlOkay
*/

println()
println("foldRight(c, r) c ++ r")
val it33 = words.foldRight("Okay") { (c, r) => println("c: " + c + " r: " + r); c ++ r }
println(it33)
/*
foldRight(c, r) c ++ r
c: girl r: Okay
c: lonely r: girlOkay
c: there r: lonelygirlOkay
c: hey r: therelonelygirlOkay
heytherelonelygirlOkay
*/


println()
println("foldLeft(m, n) m ++ n")
val it693 = words.foldLeft("Okay") { (m, n) => println("m: " + m + " n: " + n); m ++ n }
println(it693)
/*
foldLeft(m, n) m ++ n
m: Okay n: hey
m: Okayhey n: there
m: Okayheythere n: lonely
m: Okayheytherelonely n: girl
Okayheytherelonelygirl
*/

println()
println("foldLeft(r, c) r ++ c")
val it37 = words.foldLeft("Okay") { (r, c) => println("r: " + r + " c: " + c); r ++ c }
println(it37)
/*
foldLeft(r, c) r ++ c
r: Okay c: hey
r: Okayhey c: there
r: Okayheythere c: lonely
r: Okayheytherelonely c: girl
Okayheytherelonelygirl
*/


println()
println("Alternatives:")

println()
println("foldRight(c, r) r ++ c")
val it24 = words.foldRight("Okay") { (c, r) => println("c: " + c + " r: " + r); r ++ c }
println(it24)
/*
foldRight(c, r) r ++ c
c: girl r: Okay
c: lonely r: Okaygirl
c: there r: Okaygirllonely
c: hey r: Okaygirllonelythere
Okaygirllonelytherehey
*/

println()
println("foldLeft(r, c) c ++ r")
val it13 = words.foldLeft("Okay") { (r, c) => println("r: " + r + " c: " + c); c ++ r }
println(it13)
/*
foldLeft(r, c) c ++ r
r: Okay c: hey
r: heyOkay c: there
r: thereheyOkay c: lonely
r: lonelythereheyOkay c: girl
girllonelythereheyOkay
*/

println()
println("fold(r, c) r ++ c")
val it36 = words.fold("Okay") { (r, c) => println("r: " + r + " c: " + c); r ++ c }
println(it36)
/*
fold(r, c) r ++ c
r: Okay c: hey
r: Okayhey c: there
r: Okayheythere c: lonely
r: Okayheytherelonely c: girl
Okayheytherelonelygirl
*/

println()
println("fold(r, c) c ++ r")
val it43 = words.fold("Okay") { (r, c) => println("r: " + r + " c: " + c); c ++ r }
println(it43)
/*
fold(r, c) c ++ r
r: Okay c: hey
r: heyOkay c: there
r: thereheyOkay c: lonely
r: lonelythereheyOkay c: girl
girllonelythereheyOkay
*/

println()
println("foldLeft(r, c) c :: r")
val it56 = words.foldLeft(List[String]()) { (r, c) => println("r: " + r + " c: " + c); c :: r }
println(it56)
/*
foldLeft(r, c) c :: r
r: List() c: hey
r: List(hey) c: there
r: List(there, hey) c: lonely
r: List(lonely, there, hey) c: girl
List(girl, lonely, there, hey)
*/

println()
println("foldRight(c, r) c :: r")
val it76 = words.foldRight(List[String]()) { (c, r) => println("c: " + c + " r: " + r); c :: r }
println(it76)
/*
foldRight(c, r) c :: r
c: girl r: List()
c: lonely r: List(girl)
c: there r: List(lonely, girl)
c: hey r: List(there, lonely, girl)
List(hey, there, lonely, girl)
*/

// scala> :load ./scripts/my-scripts/lonely.scala
