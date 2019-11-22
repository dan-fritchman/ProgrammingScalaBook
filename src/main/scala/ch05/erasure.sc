object M {
  /*
   * This "marker" apparently papers over the JVM's inability
   * to dynamic-dispatch to these two functions.
   * */

  implicit object IntMarker

  implicit object StringMarker

  def m(seq: Seq[Int])(implicit i: IntMarker.type): Unit = {
    println(s"Seq[Int]: $seq")
  }

  def m(seq: Seq[String])(implicit s: StringMarker.type) = {
    println(s"Seq[String]: $seq")
  }
}

import M._
m(List(1,2,3))
m(List("one", "two", "three"))

