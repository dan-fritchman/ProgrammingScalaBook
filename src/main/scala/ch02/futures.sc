import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Success, Failure}


def sleep(millis: Long): Unit = {
  Thread.sleep(millis)
}

def doWork(index: Int): Int = {
  sleep(((math.random * 1000)).toLong)
  index
}

(1 to 5) foreach { index =>
  val future = Future {
    doWork(index)
  }
  future onComplete {
    case Success(answer) => println(s"Success: returned $answer")
    case Failure(th) => println(s"FAIL!: returned $th")
  }
}

sleep(1000)
println("Finito!")

