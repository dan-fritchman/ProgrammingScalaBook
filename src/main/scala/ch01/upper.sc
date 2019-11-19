object Upper {
  def upper(strings: String*): Seq[String] = strings.map(_.toUpperCase)
}

//val up = new Upper
println (Upper.upper("Hello", "World!"))
