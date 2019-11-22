sealed abstract class HttpMethod() {
  def body: String

  def bodyLength = body.length
}

case class Connect(body: String) extends HttpMethod

case class Delete(body: String) extends HttpMethod

case class Get(body: String) extends HttpMethod

case class Head(body: String) extends HttpMethod

case class Options(body: String) extends HttpMethod

case class Post(body: String) extends HttpMethod

case class Put(body: String) extends HttpMethod

case class Trace(body: String) extends HttpMethod

def handle(method: HttpMethod) = method match {
  case Connect(body) => s"Connect: (length: ${method.bodyLength}) $body"
  case Delete(body) => s"Delete: (length: ${method.bodyLength}) $body"
  case Get(body) => s"Get: (length: ${method.bodyLength}) $body"
  case Head(body) => s"Head: (length: ${method.bodyLength}) $body"
  case Options(body) => s"Options: (length: ${method.bodyLength}) $body"
  case Post(body) => s"Post: (length: ${method.bodyLength}) $body"
  case Put(body) => s"Put: (length: ${method.bodyLength}) $body"
  case Trace(body) => s"Trace: (length: ${method.bodyLength}) $body"
  // no default case, but the parent type is "sealed"
  // so there's no way to add more sub-types
}

val methods = Seq(
  Connect("Connect body..."),
  Delete("Delete body..."),
  Get("Get body..."),
  Head("Head body..."),
  Options("Options body..."),
  Post("Post body..."),
  Put("Put body..."),
  Trace("Trace body..."),
)

methods foreach (method => println(handle(method)))
