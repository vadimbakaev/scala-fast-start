package vbakaev.app.interfaces.http.impl

import java.time.Clock

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.{Content, Schema}
import io.swagger.v3.oas.annotations.responses.ApiResponse
import javax.ws.rs.{GET, Path}
import vbakaev.app.interfaces.http.commons.{Interface, JsonSupport}
import vbakaev.app.interfaces.http.models.response.StatusResponse

@Path("/status")
class StatusInterface()(implicit clock: Clock) extends Interface with JsonSupport {

  private val statusResponse = StatusResponse(clock.instant().toString)

  lazy val routes: Route = getStatus

  @GET
  @Operation(
    summary = "Return server status",
    tags = Array("server"),
    responses = Array(
      new ApiResponse(responseCode = "200",
                      description = "Server status",
                      content = Array(new Content(schema = new Schema(implementation = classOf[StatusResponse]))))
    )
  )
  def getStatus: Route =
    path("status") {
      get {
        complete(StatusCodes.OK -> statusResponse)
      }
    }

}
