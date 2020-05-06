package vbakaev.app.services

import java.time.{Clock, Instant}

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.{Content, Schema}
import io.swagger.v3.oas.annotations.responses.ApiResponse
import vbakaev.app.models.response.StatusResponse
import javax.ws.rs.{GET, Path}

@Path("/status")
class StatusService()(implicit clock: Clock) extends Service with JsonSupport {

  private val Response = StatusResponse(clock.instant().toString)

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
        complete(Response)
      }
    }

}
