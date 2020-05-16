package vbakaev.app

import java.time.Clock

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ClosedShape
import akka.stream.scaladsl.{GraphDSL, RunnableGraph, Sink, Source}
import com.typesafe.scalalogging.LazyLogging
import vbakaev.app.config.AppConfig
import akka.stream.scaladsl.GraphDSL.Implicits._
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
      error =>
        Source
          .single(s"Configuration loading error $error")
          .to(Sink.foreach(msg => logger.error(msg))),
      config =>
        RunnableGraph
          .fromGraph(GraphDSL.create() { implicit b =>
            logger.info(s"Server is running on http://${config.http.interface}:${config.http.port}/status")
            logger.info(
              s"See documentation http://${config.http.interface}:${config.http.port}/swagger-ui/index.html?url=/api-docs/swagger.json"
            )

            val serverRoutes   = new ServerRoutes(config).routes
            val httpConnection = Http()(system).bind(config.http.interface, config.http.port)
            val httpConnectionHandler = Sink.foreach[Http.IncomingConnection] { connection =>
              connection.handleWith(serverRoutes)
              ()
            }

            httpConnection ~> httpConnectionHandler

            ClosedShape
          })
    )
    .run()

}
