package vbakaev.app.interfaces.http.swagger

import akka.http.scaladsl.server.{Directives, Route}
import org.webjars.WebJarAssetLocator
import vbakaev.app.interfaces.http.commons.Interface

import scala.util.{Failure, Success, Try}

object SwaggerUIInterface extends Interface with Directives {
  private val webJarAssetLocator = new WebJarAssetLocator()
  val routes: Route = {
    pathPrefix("swagger-ui") {
      extractUnmatchedPath { path =>
        Try(webJarAssetLocator.getFullPath("swagger-ui", path.toString())) match {
          case Success(fullPath) =>
            getFromResource(fullPath)
          case Failure(_: IllegalArgumentException) =>
            reject
          case Failure(e) =>
            failWith(e)
        }
      }
    }
  }
}
