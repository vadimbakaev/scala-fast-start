package vbakaev.app.interfaces.http.commons

import akka.http.scaladsl.server.Route

trait Interface {
  def routes: Route
}
