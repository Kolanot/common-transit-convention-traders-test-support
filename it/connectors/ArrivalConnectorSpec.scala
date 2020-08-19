package connectors

import com.github.tomakehurst.wiremock.client.WireMock._
import models.{ArrivalId, MessageType}
import org.scalatest.concurrent.{IntegrationPatience, ScalaFutures}
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.must.Matchers
import org.scalatestplus.scalacheck.ScalaCheckPropertyChecks
import play.api.test.FakeRequest
import play.api.test.Helpers._
import uk.gov.hmrc.http.HeaderCarrier

import scala.concurrent.ExecutionContext.Implicits.global

class ArrivalConnectorSpec extends AnyFreeSpec with Matchers with WiremockSuite with ScalaFutures with IntegrationPatience with ScalaCheckPropertyChecks {
  private val arrivalId = new ArrivalId(1)

  "post" - {
    "must return CREATED when post is successful" in {
      val connector = app.injector.instanceOf[ArrivalConnector]

      server.stubFor(
        post(
          urlEqualTo("/transit-movements-trader-at-destination/movements/arrivals/MDTP-1-1/messages/eis")
        ).willReturn(aResponse().withStatus(CREATED))
      )

      implicit val hc = HeaderCarrier()
      implicit val requestHeader = FakeRequest()

      val result = connector.post(MessageType.ArrivalRejection.code, "<document></document>", arrivalId).futureValue

      result.status mustEqual CREATED
    }

    "must return INTERNAL_SERVER_ERROR when post" - {
      "returns INTERNAL_SERVER_ERROR" in {
        val connector = app.injector.instanceOf[ArrivalConnector]

        server.stubFor(
          post(
            urlEqualTo("/transit-movements-trader-at-destination/movements/arrivals/MDTP-1-1/messages/eis")
          ).willReturn(aResponse().withStatus(INTERNAL_SERVER_ERROR))
        )

        implicit val hc = HeaderCarrier()
        implicit val requestHeader = FakeRequest()

        val result = connector.post(MessageType.ArrivalRejection.code, "<document></document>", arrivalId).futureValue

        result.status mustEqual INTERNAL_SERVER_ERROR
      }

    }

    "must return BAD_REQUEST when post returns BAD_REQUEST" in {
      val connector = app.injector.instanceOf[ArrivalConnector]

      server.stubFor(
        post(
          urlEqualTo("/transit-movements-trader-at-destination/movements/arrivals/MDTP-1-1/messages/eis")
        ).willReturn(aResponse().withStatus(BAD_REQUEST))
      )

      implicit val hc = HeaderCarrier()
      implicit val requestHeader = FakeRequest()

      val result = connector.post(MessageType.PositiveAcknowledgement.code, "<document></document>", arrivalId).futureValue

      result.status mustEqual BAD_REQUEST
    }
  }

  override protected def portConfigKey: String = "microservice.services.transit-movements-trader-at-destination.port"
}