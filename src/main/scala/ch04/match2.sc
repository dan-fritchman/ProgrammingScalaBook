object Op extends Enumeration {
  type Op = Value

  val EQ = Value("=")
  val NE = Value("!=")
  val LTGT = Value("<>")
  val LT = Value("<")
  val LE = Value("<=")
  val GT = Value(">")
  val GE = Value(">=")
}

import Op._

case class WhereOp[T](columnName: String, op: Op, value: T)

case class WhereIn[T](columnName: String, val1: T, vals: T*)

val wheres = Seq(
  WhereIn("state", "IL", "CA", "VA"),
  WhereOp("state", EQ, "IL"),
  WhereOp("name", EQ, "Buck Trends"),
  WhereOp("age", GT, 29)
)

for (where <- wheres) {
  where match {
    case WhereIn(col, val1, vals@_*) =>
      val valStr = (val1 +: vals).mkString(", ")
      println(s"WHERE $col IN ($valStr)")
    case WhereOp(col, op, value) =>
      println(s"WHERE $col $op $value")
    case _ => println(s"ERROR: Unknown Expression $where")
  }
}

val BookExtractorRE = """Book: title=([^,]+),\s+author=(.+)""".r
val MagazineExtractorRE = """Magazine: title=([^,]+),\s+issue=(.+)""".r

val catalog = Seq(
  "Book: title=Programming Scala Second Edition, author=Dean Wampler",
  "Magazine: title=The New Yorker, issue=January 2014",
  "Unknown: text=who put this here?"
)

for (item <- catalog) {
  item match {
    case BookExtractorRE(title, author) =>
      println(s"""Book $title, written by $author""")
    case MagazineExtractorRE(title, issue) =>
      println(s"""Magazine $title, issue $issue """)
    case entry => println(s"Unrecognized Entry: $entry")
  }
}

// Work-around for run-time matching of sequence parameter-type
// e.g. telling List[String] from List[Double]
def doSeqMatch[T](seq: Seq[T]): String = seq match {
  case Nil => "nothing"
  case head +: _ => head match {
    case _: Double => "double"
    case _: String => "string"
    case _ => "unmatched element"
  }
}

for { x <- Seq(List(5.5, 5.6, 5.7), List("A", "B"), Nil)} yield {
  x match {
    case seq: Seq[_] => (s"seq ${doSeqMatch(seq)}", seq)
    case _ => ("unknown!", x)
  }
}

// Some fun compound assignments
val head +: tail = List(1,2,3)
val head1 +: head2 +: tail = Vector(1,2,3)
val Seq(a,b,c) = List(1,2,3)

