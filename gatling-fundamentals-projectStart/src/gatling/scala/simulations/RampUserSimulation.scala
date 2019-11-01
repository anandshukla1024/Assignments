package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration.DurationInt
import baseconfig.BaseSimulation

class RampUserSimulation extends BaseSimulation{

  def getAllProductDetails() =
  {
    exec(http(requestName = "Get all Product Details -->")
      .get("api/product/")
      .check(status.is(expected = 200)))
  }

  def getProductDetailsById() =
  {
    exec(http(requestName = "Get Prduct Details by Id")
      .get("api/product/6")
      .check(status.in(expected = 200 to 210)))
  }

  val scn = scenario(scenarioName = "Get Product Details")
    .exec(getAllProductDetails())
    .pause(duration = 5)
    .exec(getProductDetailsById())
    .pause(duration = 5)
    .exec(getAllProductDetails())

  // Load Scenarios

  setUp(
    scn.inject(
      nothingFor(5 seconds),
      //constantUsersPerSec(rate = 10)during(10 seconds),
      rampUsersPerSec(rate1 = 1) to (5) during(20 seconds))
  ).protocols(httpConf.inferHtmlResources())


}
