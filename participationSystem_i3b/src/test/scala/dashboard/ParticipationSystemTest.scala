package es.uniovi.asw

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._


class ParticipationSystemTest extends Simulation {

  val httpConf = http.baseURL("http://localhost:8092")

  val scn = scenario("ParticipationSystemTest").
    exec(http("Root").get("/")).
    pause(3).
    exec(http("Root").get("/login")).
    pause(3)

  setUp(scn.inject(rampUsers(1000) over (60 seconds))).
    protocols(httpConf)

}
