package ch01.shapes

import akka.actor.{Props, Actor, ActorRef, ActorSystem}
import com.typesafe.config.ConfigFactory

private object Start

object ShapesDrawingDriver {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("DrawingActorSystem", ConfigFactory.load())
    val drawer = system.actorOf(Props(new ShapesDrawingActor), "drawingActor")
    val driver = system.actorOf(Props(new ShapesDrawingDriver(drawer)), "drawingService")
    driver ! Start
  }
}

class ShapesDrawingDriver(drawerActor: ActorRef) extends Actor {

  import Messages._

  def receive = {
    case Start =>
      drawerActor ! Circle(Point(0, 0), 1)
      drawerActor ! Rect(Point(0, 0), 2, 5)
      drawerActor ! 3.14159
      drawerActor ! Triangle(Point(0, 0), Point(2, 0), Point(1, 2))
      drawerActor ! Exit
    case Finished =>
      println(s"ShapesDrawingDriver: cleaning up...")
      context.system.terminate()
    case response: Response =>
      println("ShapresDrawingDriver: Response = " + response)
    case unexpected =>
      println("ShapesDrawingDriver: ERROR: Received unexpected message " + unexpected)
  }
}

object Messages {

  object Exit

  object Finished

  case class Response(message: String)

}


class ShapesDrawingActor extends Actor {

  import Messages._

  def receive = {
    case s: Shape =>
      s.draw(str => println(s"ShapesDrawingActor: $str"))
      sender ! Response(s"ShapesDrawingActor: $s drawn")
    case Exit =>
      println(s"ShapesDrawingActor: exiting...")
      sender ! Finished
    case unexpected =>
      val response = Response(s"ERROR: Unknown message: $unexpected")
      println(s"ShapesDrawingActor: $response")
      sender ! response
  }
}

case class Point(x: Double = 0.0, y: Double = 0.0)

abstract class Shape() {
  def draw(f: String => Unit): Unit = f(s"draw: $this.toString")
}

case class Circle(center: Point, radius: Double) extends Shape

case class Rect(lowerLeft: Point, height: Double, width: Double) extends Shape

case class Triangle(point1: Point, point2: Point, point3: Point) extends Shape

