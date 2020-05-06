package vbakaev.app.services.swagger

import com.github.swagger.akka.SwaggerHttpService
import com.github.swagger.akka.model.Info
import vbakaev.app.config.ServerConfig
import vbakaev.app.services.Service

class SwaggerService(serverConfig: ServerConfig, services: Set[Service]) extends Service with SwaggerHttpService {
  override val apiClasses: Set[Class[_]]        = services.map(_.getClass)
  override val host                             = s"${serverConfig.interface}:${serverConfig.port}"
  override val info                             = Info(version = "1.0")
  override val unwantedDefinitions: Seq[String] = Seq("Function1", "Function1RequestContextFutureRouteResult")
}
