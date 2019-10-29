import baseconfig.BaseSimulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class MyFirstTest extends BaseSimulation {

  // 1 Common HTTP Configuration

  // 2 Scenario Definition

  val scnGetProductById = scenario(scenarioName = "Get Product By Id")
    .exec(http(requestName = "Get Product details based on id").get("api/product/1"))

  // 3 Load Scenario
  setUp(
    scnGetProductById.inject(atOnceUsers(users = 2)).protocols(httpConf)
  )

}
