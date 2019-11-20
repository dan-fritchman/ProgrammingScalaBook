package ch03.arm

import scala.language.reflectiveCalls
import scala.util.control.NonFatal

object manage {
  def apply[R <: {def close() : Unit}, T]
  (resource: => R)(f: R => T): T = {
    var res: Option[R] = None
    try {
      res = Some(resource)
      f(res.get)
    } catch {
      case NonFatal(ex) =>
        println(s"manage.apply(): Non-fatal exception: $ex")
        throw ex
    } finally {
      if (res != None) {
        println(s"Closing resources...")
        res.get.close
      }
    }
  }
}

object TryCatchARM {
  def main(args: Array[String]) = {
    val sizes = args.map(arg => returnFileLength(arg))
    println("Returned sizes: " + (sizes.mkString(", ")))
  }

  import scala.io.Source

  def returnFileLength(fileName: String): Int = {
    println()
    manage(Source.fromFile(fileName)) { source =>
      val size = source.getLines.size
      println(s"file $fileName has $size lines")
      if (size > 200) throw new RuntimeException(s"Big File!")
      size
    }
  }
}