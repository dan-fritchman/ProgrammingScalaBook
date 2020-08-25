/*
 * Demos of how `for` gets mapped to collection methods
 * */

val states = List("Alabama", "Alaska", "Virginia")
for {s <- states}
  println(s)
states.foreach(println)

for {s <- states}
  yield s.toUpperCase

states.map(_.toUpperCase)

// Now do a "2x nested" for
for {
  s <- states
  c <- s
} yield s"$c-${c.toUpper}"

// Is the same as
states.flatMap(_.toSeq).map(c => s"$c-${c.toUpper}")

// With a guard:
for {
  s <- states
  c <- s
  if c.isLower
} yield s"$c-${c.toUpper} "

// Corresponds to:
states.flatMap(_.toSeq.withFilter(_.isLower).map(c => s"$c-${c.toUpper} "))

// Defining a variable inline
for {
  s <- states
  c <- s
  if c.isLower
  c2 = s"$c-${c.toUpper} "
} yield c2

// Corresponds to:
states.flatMap(_.toSeq.withFilter(_.isLower).map { c =>
  val c2 = s"$c-${c.toUpper} "
  c2
})

// This @ assignment syntax
val z@(x, y) = (1, 2)

val map = Map("one" -> 1, "two" -> 2)
val list1 = for {
  (key, value) <- map
  i10 = value + 10
} yield (i10)

// Corresponds to
val list2 = for {
  (i, i10) <- for {
    x1@(key, value) <- map
  } yield {
    val x2@i10 = value + 10
    (x1, x2)
  }
} yield (i10)

