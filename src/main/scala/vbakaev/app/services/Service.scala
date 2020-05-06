package vbakaev.app.services

import akka.http.scaladsl.server.Route

trait Service {
  def routes: Route
}
