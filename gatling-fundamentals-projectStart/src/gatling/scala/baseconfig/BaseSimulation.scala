package baseconfig

import io.gatling.core.Predef._
import io.gatling.http.Predef._

class BaseSimulation extends Simulation {

  // 1 Common HTTP Configuration

  val httpConf = http
    .baseURL(url = "http://localhost.fiddler:8102/")
    .header(name = "Accept", value = "application/json")
//    .proxy(Proxy("localhost",8888).httpsPort(port = 8888))

}
