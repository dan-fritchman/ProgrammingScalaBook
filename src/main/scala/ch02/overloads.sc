
object StringUtilV1 {
  def joiner(strings: String*): String = strings.mkString("-")

  // This function needs a return-type definition.
  def joiner(strings: List[String]): String = joiner(strings: _*)
}

println(StringUtilV1.joiner(
  List("Prog", "Scala")
))

def makeList(strings: String*): List[String] = {
  if (strings.length == 0)
    Nil
  else
    strings.toList
}

val list: List[String] = makeList()

val hello =
  """
    |Hello you!
    |This auto-strip-margin mode
    |is pretty nice eh.
    |""".stripMargin

def goodbye(name: String) = {
  s"""xxxGoodbye, ${name}yyy
     xxxCome again!yyy""".stripPrefix("xxx").stripSuffix("yyy")
}

println(goodbye("Prog Scala"))

val t1: (Int, String) = (1, "two")
val t2: Tuple2[Int, String] = (1, "two")

val t = ("Hello", 1, 2.3)
println("Print the whole tuple: " + t)
println("Print the first item: " + t._1)
println("Print the second item: " + t._2)
println("Print the third item: " + t._3)

val (t1, t2, t3) = ("World", "!", 0x22)
println(t1 + ", " + t2 + ", " + t3)
val (t4, t5, t6) = Tuple3("World", "!", 0x22)
println(t4 + ", " + t5 + ", " + t6)

val tt1 = (1, "one")
val tt2 = 1 -> "one"
val tt3 = Tuple2(1, "one")

val stateCapitals = Map(
  "Alabama" -> "Montgomery",
  "Wyoming" -> "Cheyenne"
)
println(stateCapitals.get("Alabama"))
println(stateCapitals.get("Pennsylvania"))


def stuffWithBigInt() = {
  import java.math.BigInteger.{
    ONE => _,
    TEN,
    ZERO => JAVAZERO,
  }
  println("TEN: " + TEN)
  println("ZERO: " + JAVAZERO)
}
stuffWithBigInt()

val strings: List[String] = List("one", "two", "three")


import java.io._

abstract class BulkReader {
  type In
  val source: In

  def read: String
}

class StringBulkReader(val source: String) extends BulkReader {
  type In = String

  def read: String = source
}

class FileBulkReader(val source: File) extends BulkReader {
  type In = File

  def read: String = {
    val in = new BufferedInputStream(
      new FileInputStream(source)
    )
    val numBytes = in.available()
    val bytes = new Array[Byte](numBytes)
    in.read(bytes, 0, numBytes)
    new String(bytes)
  }
}

println(new StringBulkReader(
  "Hello Scala!").read)
println(new FileBulkReader(
  new File("ch02/abstract.sc")).read)