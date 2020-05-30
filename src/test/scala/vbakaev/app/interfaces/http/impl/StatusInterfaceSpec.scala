package vbakaev.app.interfaces.http.impl

import java.time.{Clock, LocalDateTime, ZoneOffset}

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import vbakaev.app.interfaces.http.commons.Interface

class StatusInterfaceSpec extends AnyWordSpecLike with Matchers with ScalatestRouteTest {

  "The status interface" when {
    "get" should {
      "return 200 with date of date time of deploy" in {
        implicit val fixedClock: Clock =
          Clock.fixed(LocalDateTime.of(2020, 12, 31, 23, 59, 59).toInstant(ZoneOffset.UTC), ZoneOffset.UTC)
        val interface: Interface = new StatusInterface()

        Get("/status") ~> interface.routes ~> check {
          status shouldEqual StatusCodes.OK
          responseAs[String] shouldEqual """{"startedAt":"2020-12-31T23:59:59Z","status":"OK"}"""
        }
      }
    }
  }

}
