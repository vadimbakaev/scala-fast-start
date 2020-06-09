package vbakaev.app.interfaces.http.impl

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpecLike
import vbakaev.app.interfaces.http.commons.Interface

class DocsInterfaceSpec extends AnyWordSpecLike with Matchers with ScalatestRouteTest {

  "The docs interface" when {
    "get" should {
      "return 308 with swagger reference" in {
        val interface: Interface = new DocsInterface()

        Get("/docs") ~> interface.routes ~> check {
          status shouldEqual StatusCodes.PermanentRedirect
          responseAs[String] shouldEqual """The request, and all future requests should be repeated using <a href="swagger-ui/index.html?url=/api-docs/swagger.json">this URI</a>."""
        }
      }
    }
  }

}
