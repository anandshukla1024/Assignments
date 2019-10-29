package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import baseconfig.BaseSimulation

class CheckResponseCode extends BaseSimulation {

  //Check Specific Status Code
  val scn = scenario(scenarioName = "Get Product Details")
    .exec(http(requestName = "Get all Product Details --> 1st call")
      .get("api/product/")
      .check(status.is(expected = 400)))

    //Check Status Code in Range
    .exec(http(requestName = "Get Prduct Details by Id")
      .get("api/product/1")
      .check(status.in(expected = 200 to 210)))

    //Check Status Code is NOT
    .exec(http(requestName = "Get all Product Details --> 2nd call")
      .get("api/product/")
      .check(status.not(expected = 400), status.not(expected = 500)))

  // Load Scenarios

  setUp(
    scn.inject(atOnceUsers(users = 1))
  ).protocols(httpConf)

}
