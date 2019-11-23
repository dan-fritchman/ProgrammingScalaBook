/*
 * Not really sure what's wrong here.
 *
 * if (xs.isEmpty) done(true) else tailcall(isOdd(xs.tail)) //1
 * ^
 * On line 2: error: not found: value isOdd
 */

import scala.util.control.TailCalls._

def isEven(xs: List[Int]): TailRec[Boolean] =
  if (xs.isEmpty) done(true) else tailcall(isOdd(xs.tail))

def isOdd(xs: List[Int]): TailRec[Boolean] =
  if (xs.isEmpty) done(true) else tailcall(isEven(xs.tail))

isEven(List(4))

for (i <- 1 to 5) {
  val even = isEven((1 to i).toList).result
  println(s"$i is even? $even")
}

