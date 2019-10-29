package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration.DurationInt

import baseconfig.BaseSimulation

class AddPauseTime extends BaseSimulation {

  // Scenarios Definition

  val scn = scenario(scenarioName = "Get Product Details")
    .exec(http(requestName = "Get all Product Details --> 1st call")
      .get("api/product/"))
    .pause(duration = 5)

    .exec(http(requestName = "Get Prduct Details by Id")
      .get("api/product/1"))
    .pause(1, 20)

    .exec(http(requestName = "Get all Product Details --> 2nd call")
      .get("api/product/"))
    .pause(duration = 3000.milliseconds)

  // Load Scenarios

setUp(
  scn.inject(atOnceUsers(users = 1))
).protocols(httpConf)
}
