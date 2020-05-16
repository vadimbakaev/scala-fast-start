package vbakaev.app

import java.time.Clock

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import com.typesafe.scalalogging.LazyLogging
import vbakaev.app.config.{AppConfig, ServerConfig}
import pureconfig.ConfigSource
import pureconfig.generic.auto._

import scala.concurrent.ExecutionContextExecutor

object Main extends App with LazyLogging {
  implicit val clock: Clock                               = Clock.systemUTC()
  implicit val system: ActorSystem                        = ActorSystem()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  ConfigSource.default
    .load[AppConfig]
    .fold(
      error => logger.error(s"Configuration loading error $error"),
      config => {
        logger.info(s"App starting with configuration: $config")
        val ServerConfig(interface, port) = config.http

        val serverRoutes = new ServerRoutes(config).routes
        Http().bindAndHandle(serverRoutes, interface, port)

        logger.info(s"Server is running on http://$interface:$port/status")
        logger.info(s"See documentation http://$interface:$port/swagger-ui/index.html?url=/api-docs/swagger.json")
      }
    )

  logger.info("Running...")

}
