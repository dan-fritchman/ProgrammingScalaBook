//package ch02.shapes

case class Point(x: Double = 0.0, y: Double = 0.0) {
  def shift(deltax: Double = 0.0, deltay: Double = 0.0) =
    copy(x + deltax, y + deltay)
}

val p1 = new Point(x = 3.3, y = 4.4)
val p2 = p1.copy(y = 6.6)

abstract class Shape() {
  def draw(offset: Point = Point(0, 0))(f: String => Unit): Unit =
    f(s"draw(offset = $offset), ${this.toString}")
}

case class Circle(center: Point, radius: Double) extends Shape

case class Rect(lowerLeft: Point, height: Double, width: Double) extends Shape

val s = new Rect(Point(0, 0), 1, 1)
s.draw(Point(0, 0)) { str =>
  println(s"ShapesDrawingActor: $str")
}

def m1[A](a: A, f: A => String) = f(a)
def m2[A](a: A)(f: A => String) = f(a)

//m1(100, i => s"$i + $i")
m2(100)(i => s"$i + $i")

