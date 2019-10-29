package simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import baseconfig.BaseSimulation

class CheckResponseBodyAndExtract extends BaseSimulation {

  //validate with Response Body

  val scn = scenario(scenarioName = "Get Product Details")
    .exec(http(requestName = "Get Prduct Details by Id --> 1st call")
      .get("api/product/6")
      .check(jsonPath(path = "$.name").is(expected = "Bread - White, Unsliced"), jsonPath(path = "$.price").is(expected = "969")))

  // Extract data from Response and pass it to next scenarios and validate the same.

    .exec(http(requestName = "Get all Product Details --> 2nd call")
      .get("api/product/")
        .check(jsonPath(path = "$[2].id").saveAs(key = "productId")))
    //Debugging ==> use of session variable or Response Body can be used for debugging purpose
    .exec { session => println(session); session}


  // Pass the saved session variables in next scenarios i.e: productId

      .exec(http(requestName = "Get Specific product based on parameter passed --> 2nd call")
      .get("api/product/${productId}")
      .check(jsonPath(path = "$.id").is(expected = "6"),jsonPath(path = "$.name").is(expected = "Bread - White, Unsliced"))
      .check(bodyString.saveAs(key = "responseBody")))
      .exec{ session => println(session("responseBody").as[String]);session}



  // Load Scenarios

  setUp(
    scn.inject(atOnceUsers(users = 1))
  ).protocols(httpConf)


}
