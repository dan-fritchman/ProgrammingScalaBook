package ch03.enum

object Breed extends Enumeration {
  type Breed = Value
  val doberman = Value("Doberman")
  val pug = Value("Pug")
  val yorkie = Value("Yorkshire Terrier")
  val scottie = Value("Scottish Terrier")
}

object WeekDay extends Enumeration {
  type WeekDay = Value
  val Mon, Tues, Weds, Thurs, Fri, Sat, Sun = Value
}

object Enumerator {
  def main(args: Array[String]): Unit = {
    import Breed._

    println("ID\tBreed")
    for (breed <- Breed.values) println(s"${breed.id}\t$breed")

    def isTerrier(b: Breed) = b.toString.endsWith("Terrier")

    println("Terriers: ")
    Breed.values.filter(isTerrier).foreach(println)

    import WeekDay._
    def isWorkDay(d:WeekDay) = !(d==Sat || d==Sun)
    WeekDay.values.filter(isWorkDay).foreach(println)

  }
}
