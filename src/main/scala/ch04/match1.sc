val bools = Seq(true, false)

for (bool <- bools) {
  bool match {
    case true => println("Got heads")
    case false => println("Got tails")
  }
}

for {x <- Seq(1, 2, 2.7, "one", "two", 'four)} {
  val str = x match {
    case 1 => "int 1"
    case _: Int | _: Double => "a number: " + x
    case "one" => "string one"
    case _: String => "other string: " + x
    case _ => "unexpected value: " + x
  }
  println(str)
}

def checkY(y: Int) = {
  for {x <- Seq(99, 100, 101)} {
    val str = x match {
      // These back-ticks are necessary to catch the *value* of y
      // Otherwise we get a new variable y
      case `y` => "found y!"
      case i: Int => "int: " + i
    }
    println(str)
  }
}
checkY(100)

val nonEmptySeq = Seq(1, 2, 3, 4, 5)
val emptySeq = Seq.empty[Int]
val nonEmptyList = List(1, 2, 3, 4, 5)
val emptyList = Nil // ?
val nonEmptyVector = Vector(1, 2, 3, 4, 5)
val emptyVector = Vector.empty[Int]
val nonEmptyMap = Map("one" -> 1, "two" -> 2, "three" -> 3)
val emptyMap = Map.empty[String, Int]

def seqToString[T](seq: Seq[T]): String =
  seq match {
    case head +: tail => s"$head +: " + seqToString(tail)
    case Nil => "nil"
  }

for (seq <- Seq(
  nonEmptySeq, emptySeq,
  nonEmptyList, emptyList,
  nonEmptyVector, emptyVector,
  nonEmptyMap.toSeq, emptyMap.toSeq
)) {
  println(seqToString(seq))
}

val s2 = (("one", 1) +: ("two", 2) +: ("three", 3) +: Nil)
val m = Map(s2: _*)

val langs = Seq(
  ("Scala", "Martin", "Odersky"),
  ("Clojure", "Rich", "Hickey"),
  ("Lisp", "John", "McCarthy")
)
for (tuple <- langs) {
  tuple match {
    case ("Scala", _, _) => println("Found Scala")
    case (lang, first, last) =>
      println(s"Found other language: $lang ($first, $last)")
  }
}

for (i <- Seq(1, 2, 3, 4)) {
  i match {
    case _ if i % 2 == 0 => println(s"even: $i")
    case _ => println(s"odd: $i")
  }
}

case class Address(street: String, city: String, country: String)

case class Person(name: String, age: Int, address: Address)

val alice = Person("Alice", 25, Address("1 Scala Lane", "Chicago", "USA"))
val bob = Person("Bob", 29, Address("2 Java Ave", "Miami", "USA"))
val charlie = Person("Charlie", 32, Address("3 Python Ct", "Boston", "USA"))

for (person <- Seq(alice, bob, charlie)) {
  person match {
    case Person("Alice", 25, Address(_, "Chicago", _)) => println("Who TF is Alice?")
    case Person("Bob", 29, Address("2 Java Ave", "Miami", "USA")) => println("Hi Bob!")
    case Person(name, age, _) => println(s"Who dis $age year-old?")
  }
}

val itemsCost = Seq(("Pencil", 0.52), ("Paper", 1.35), ("Notebook", 2.43))
val itemsCostIndices = itemsCost.zipWithIndex
for (itemCostIndex <- itemsCostIndices) {
  itemCostIndex match {
    case ((item, cost), index) => println(s"$index: $item costs $cost")
  }
}

case class With[A, B](a: A, b: B)

val with1: With[String, Int] = With("Foo", 1)
// In-fix type notation
// Resolves similarly to `with1`
// Seriously WTF are these guys thinking
val with2: String With Int = With("Bar", 2)

Seq(with1, with2) foreach { w =>
  w match {
    case s With i => println(s"$s with $i")
    case _ => println(s"Unknown: $w")
  }
}

def revSeqToString[T](l: Seq[T]): String = l match {
  case prefix :+ end => revSeqToString(prefix) +
    s" :+ $end"
  case Nil => "nil"
}
for (seq <- Seq(nonEmptyList, nonEmptyVector, nonEmptyMap.toSeq)) {
  println(revSeqToString(seq))
}

def windows[T](seq: Seq[T]): String = seq match {
  case head1 +: head2 +: tail =>
    s"($head1, $head2), " + windows(seq.tail)
  case head +: tail =>
    s"($head, _), " + windows(seq.tail)
  case Nil => "nil"
}

for (seq <- Seq(nonEmptyList, emptyList, nonEmptyMap.toSeq)) {
  println(windows(seq))
}

// Sliding sequence methods
val seq = Seq(1, 2, 3, 4, 5)
val slide2 = seq.sliding(2)
val slide2seq = slide2.toSeq
val slide2list = slide2.toList
val slide32list = seq.sliding(3, 2).toList


