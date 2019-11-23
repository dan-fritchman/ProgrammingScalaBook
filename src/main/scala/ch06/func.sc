var factor = 2
val multiplier = (i: Int) => i * factor

(1 to 10).filter(_ % 2 == 0).map(multiplier).reduce(_ * _)

factor = 3
// This changes the `factor` reference in `multiplier`
(1 to 10).filter(_ % 2 == 0).map(multiplier).reduce(_ * _)

def m1(multiplier: Int => Int): Int = {
  (1 to 10).filter(_ % 2 == 0).map(multiplier).reduce(_ * _)
}

def m2: Int => Int = {
  val factor = 2
  val multiplier = (i: Int) => i * factor
  multiplier
}

m1(m2)

object Multiplier {
  var factor = 2

  def multiplier(i: Int) = i * factor
}

(1 to 10).filter(_ % 2 == 0).map(Multiplier.multiplier).reduce(_ * _)

Multiplier.factor = 3
(1 to 10).filter(_ % 2 == 0).map(Multiplier.multiplier).reduce(_ * _)

import scala.annotation.tailrec

// This is "not quite" a tail recursion.
//@tailrec
def factorial(i: BigInt): BigInt = {
  if (i == 1) i
  else i * factorial(i - 1)
}

def factorial(i: BigInt): BigInt = {
  @tailrec // *This* is tail-recursive
  def fact(i: BigInt, accum: BigInt): BigInt = {
    if (i == 1) accum
    else fact(i - 1, i * accum)
  }

  fact(i, 1)
}

for (i <- 1 to 10)
  println(s"$i:\t${factorial(i)}")

// Only the tail-recursive version works here
// The naive one generates stack overflows.
factorial(10000)

def cat1(s1: String)(s2: String): String = s1 + s2
val hello = cat1("Hello ")(_) // partial application
hello("cat")

// Currying version
def cat2(s1: String) = (s2: String) => s1 + s2
val cat2hello = cat2("hello ")
cat2hello("dog")

def cat3(s1: String, s2: String) = s1 + s2
cat3("hello ", "world")
val cat3curried = (cat3 _).curried
cat3curried("hello ")("world")

val f1: String => String => String =
  (s1: String) => (s2: String) => s1 + s2
val f2: String => (String => String) =
  (s1: String) => (s2: String) => s1 + s2
f1("hello ")("pug")
f1("hello ")("dan")

val cat3uncurried = Function.uncurried(cat3curried)
cat3uncurried("hello ", "parisiens")

val ff1 = Function.uncurried(f1)
cat3uncurried("hello ", "you")

def multiplier(i: Int)(factor: Int) = i * factor
val byFive = multiplier(5)(_)
val byTen = multiplier(10)(_)
byFive(2)
byTen(2)

// Tuple / un-tuple-izing
def mult(d1: Double, d2: Double, d3: Double) = d1 * d2 * d3
val multTupled = Function.tupled(mult _)
multTupled((1, 2, 3)) // this is a tuple argument
val multUntupled = Function.untupled(multTupled)
multUntupled(1, 2, 3)

val finicky: PartialFunction[String, String] = {
  case "finicky" => "FINICKY"
}

finicky("finicky")
//finicky("other") // MatchError
val finickyOption = finicky.lift
finickyOption("finicky")
finickyOption("other")
val finicky2 = Function.unlift(finickyOption)
finicky2("finicky")

