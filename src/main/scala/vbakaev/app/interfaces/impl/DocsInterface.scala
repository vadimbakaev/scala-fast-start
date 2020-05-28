package vbakaev.app.interfaces.impl

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import vbakaev.app.interfaces.commons.Interface

class DocsInterface extends Interface {

  lazy val routes: Route = getDocs

  def getDocs: Route =
    path("docs") {
      get {
        redirect("swagger-ui/index.html?url=/api-docs/swagger.json", StatusCodes.PermanentRedirect)
      }
    }

}
