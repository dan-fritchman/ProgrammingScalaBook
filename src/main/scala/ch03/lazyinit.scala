package ch03.lazyinit


object ExpensiveResource {
  lazy val resource: Int = init()

  def init(): Int = {
    println("initializing")
    0 // "expensive"
  }

  def main(args: Array[String]) = {
    val zero = resource
    println(zero)
  }
}

