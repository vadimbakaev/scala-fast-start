package vbakaev.app.interfaces

import java.time.Clock

import akka.http.scaladsl.model.headers.HttpOrigin
import akka.http.scaladsl.server.{Route, RouteConcatenation}
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import ch.megard.akka.http.cors.scaladsl.model.HttpOriginMatcher.Default
import ch.megard.akka.http.cors.scaladsl.settings.CorsSettings
import vbakaev.app.config.AppConfig
import vbakaev.app.interfaces.commons.Interface
import vbakaev.app.interfaces.impl.{DocsInterface, StatusInterface}
import vbakaev.app.interfaces.swagger.{SwaggerInterface, SwaggerUIInterface}

class ServerRoutes(appConfig: AppConfig)(implicit clock: Clock) extends RouteConcatenation {

  private val documentedServices: Set[Interface] = Set(
    new StatusInterface()
  )

  private val services: Set[Interface] = Set(
    SwaggerUIInterface,
    new SwaggerInterface(appConfig.http.appRoot, documentedServices),
    new DocsInterface
  )

  private val settings = CorsSettings.defaultSettings.withAllowedOrigins(
    Default(
      List(
        HttpOrigin("http://localhost:3000")
      )
    )
  )

  val routes: Route = cors(settings) { (services ++ documentedServices).map(_.routes).reduce(_ ~ _) }

}
