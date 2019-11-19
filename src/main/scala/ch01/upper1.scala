package ch01

object Upper {
  def main(args: Array[String]) = {
    val output = args.map(_.toUpperCase()).mkString("[", ", ", "]");
    println(output);
  }
}