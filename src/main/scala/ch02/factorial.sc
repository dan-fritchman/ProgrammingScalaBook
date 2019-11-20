
import scala.annotation.tailrec

def factorial(i: Int): Long = {
  @tailrec
  def fact(i: Int, accum: Long): Long = {
    if (i <= 1) accum
    else fact(i - 1, i * accum)
  }

  fact(i, 1)
}

(0 to 5) foreach (i => println(factorial(i)))

def countTo(n: Int): Unit = {
  def count(i: Int): Unit = {
    if (i <= n) {
      println(i)
      count(i + 1)
    }
  }
  count(1)
}

countTo(5)

