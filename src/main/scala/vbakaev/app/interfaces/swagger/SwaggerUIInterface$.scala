package vbakaev.app.interfaces.swagger

import akka.http.scaladsl.server.{Directives, Route}
import vbakaev.app.interfaces.Interface

object SwaggerUIInterface$ extends Interface with Directives {
  val routes: Route =
  path("swagger") { getFromResource("swagger/index.html") } ~
  getFromResourceDirectory("swagger")
}
