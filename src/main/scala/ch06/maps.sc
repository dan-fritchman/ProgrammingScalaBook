val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Alaska" -> "Juneau",
  "Wyoming" -> "Cheyenne"
)
val lengths = stateCapitals.map {
  kv => (kv._1, kv._2.length)
}

val caps = stateCapitals.map {
  case (k, v) => (k, v.toUpperCase)
}

val stateCapitals2 = stateCapitals + (
  "Virginia" -> "Richmond"
  )

val stateCapital3 = stateCapitals2 + (
  "New York" -> "Albany",
  "Illinois" -> "Springfield"
)

val states = Set("Alabama", "Alaska", "Wyoming")
val lengths = states.map(st => st.length)
val states2 = states + "Virginia"
val states3 = states2 + ("New York", "Illinois")

List(1, 2, 3, 4, 5).foreach {
  i => println("Int: " + i)
}

stateCapitals.foreach {
  case (k, v) => println(k + ":" + v)
}

val now = List("now", "is", "", "the", "time")
now.flatMap(s => s.toList)
now.map(s => s.toList).flatten

// Filtering
val map2 = stateCapitals.filter(kv => kv._1.startsWith("A"))

// Folding & Reducing
val list = List(1, 2, 3, 4, 5, 6)
list.reduce(_ + _)
list.fold(10)(_ * _)

List.empty[Int].fold(10)(_ + _) // this works
//List.empty[Int].reduce(_ + _) // error, nothing to add
List.empty[Int].reduceOption(_ + _) // return an Option, in this case None

List(1, 2, 3, 4, 5, 6).foldRight(List.empty[String]) {
  (x, list) => ("[" + x + "]") :: list
}

list.scan(10)(_ + _)

// Left vs Right Traversals
List(1, 2, 3, 4, 5).fold(10)(_ * _)
List(1, 2, 3, 4, 5).foldLeft(10)(_ * _)
List(1, 2, 3, 4, 5).foldRight(10)(_ * _)
// all same result

List(1, 2, 3, 4, 5).reduce(_ * _)
List(1, 2, 3, 4, 5).reduceLeft(_ * _)
List(1, 2, 3, 4, 5).reduceRight(_ * _)
// again, all same result

val facLeft = (accum: Int, x: Int) => accum + x
val facRight = (x: Int, accum: Int) => accum + x
val list1 = List(1, 2, 3, 4, 5)
list1.reduceLeft(facLeft)
list1.reduceRight(facRight)

// for non-commutative functions, this wont work as well
val fncLeft = (accum: Int, x: Int) => accum - x
val fncRight = (x: Int, accum: Int) => accum - x
list1.reduceLeft(fncLeft)
list1.reduceRight(fncRight)

val fnacLeft = (x: String, y: String) => s"($x)-($y)"
val fnacRight = (x: String, y: String) => s"($x)-($y)"
val list2 = list1.map(_.toString)
list2.reduceLeft(fnacLeft)
list2.reduceRight(fnacRight)

// Tail Recursion & Traversal

import scala.math.BigInt

// Lazy fib sequence
val fibs: LazyList[BigInt] =
  BigInt(0) #:: BigInt(1) #:: fibs.zip(fibs.tail).map(n => n._1 + n._2)

fibs.take(10).foreach(i=> println(i))

