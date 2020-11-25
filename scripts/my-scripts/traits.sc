sealed trait Shape

case class Circle(r: Double) extends Shape

case class Square(s: Double) extends Shape

case class Triangle(b: Double, h: Double) extends Shape

def area(s: Shape): Double = s match {
  case Circle(r) => 3.14159 * (r * r)
  case Square(s) => s * s
  case Triangle(b, h) => (b * h) / 2
}

val shapes: Array[Shape] = Array(Circle(6), Square(6), Triangle(6, 6))

for (s <- shapes) println(area(s))

sealed trait Color

object Red extends Color

object Yellow extends Color

object Green extends Color

def announce(c: Color): String = c match {
  case Red => "Stop"
  case Yellow => "Slow"
  case Green => "Go"
}

val lights: Array[Color] = Array(Yellow, Red, Green)

for (l <- lights) println(announce(l))
