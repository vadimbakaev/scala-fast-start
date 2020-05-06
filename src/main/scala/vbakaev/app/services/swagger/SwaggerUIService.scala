package vbakaev.app.services.swagger

import akka.http.scaladsl.server.{Directives, Route}
import vbakaev.app.services.Service

object SwaggerUIService extends Service with Directives {
  val routes: Route =
  path("swagger") { getFromResource("swagger/index.html") } ~
  getFromResourceDirectory("swagger")
}
