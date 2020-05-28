package vbakaev.app.interfaces.commons

import akka.http.scaladsl.server.Route

trait Interface {
  def routes: Route
}
