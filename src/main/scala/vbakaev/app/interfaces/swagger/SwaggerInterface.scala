package vbakaev.app.interfaces.swagger

import com.github.swagger.akka.SwaggerHttpService
import com.github.swagger.akka.model.Info
import vbakaev.app.config.ServerConfig
import vbakaev.app.interfaces.Interface

class SwaggerInterface(serverConfig: ServerConfig, services: Set[Interface]) extends Interface with SwaggerHttpService {
  override val apiClasses: Set[Class[_]]        = services.map(_.getClass)
  override val host: String                     = s"${serverConfig.interface}:${serverConfig.port}"
  override val info: Info                       = Info(version = "1.0")
  override val unwantedDefinitions: Seq[String] = Seq("Function1", "Function1RequestContextFutureRouteResult")
}
