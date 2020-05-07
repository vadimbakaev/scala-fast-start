package vbakaev.app.interfaces

import akka.http.scaladsl.server.Route

trait Interface {
  def routes: Route
}
