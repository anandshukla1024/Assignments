package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import baseconfig.BaseSimulation

class CsvFeeder extends BaseSimulation {

  val csvFeeder = csv(fileName = "productCSVFile.csv").circular

  def getProductDetailsById() = {
    repeat(times = 10) {
      feed(csvFeeder)
      exec(http(requestName = "Get Product By ID")
        .get("api/product/$.id")
        .check(jsonPath(path = "$.name").is(expected = "$.name"))
        .check(status.is(expected = 200)))
        .pause(duration = 1)
    }

  }

  val scn = scenario(scenarioName = "Get Product Details by Id -->")
    .exec(getProductDetailsById())

  setUp(
    scn.inject(atOnceUsers(users = 1))
  ).protocols(httpConf)

}
