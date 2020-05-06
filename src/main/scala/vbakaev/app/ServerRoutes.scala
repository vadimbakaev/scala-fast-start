package vbakaev.app

import java.time.Clock

import akka.http.scaladsl.server.{Route, RouteConcatenation}
import vbakaev.app.config.AppConfig
import vbakaev.app.services._
import vbakaev.app.services.swagger.{SwaggerService, SwaggerUIService}

class ServerRoutes(appConfig: AppConfig)(implicit clock: Clock) extends RouteConcatenation {

  private val documentedServices: Set[Service] = Set(
    new StatusService()
  )

  private val services: Set[Service] = Set(
    SwaggerUIService,
    new SwaggerService(appConfig.http, documentedServices)
  )

  val routes: Route = (services ++ documentedServices).map(_.routes).reduce(_ ~ _)

}
