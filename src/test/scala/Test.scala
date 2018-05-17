import io.gatling.core.Predef._
import io.gatling.http.Predef._

class Test extends Simulation{

  val httpConf = http
    //.baseURL("http://computer-database.gatling.io") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")


  val scn = scenario("Sample Rest Service Test")
  .group("Perf Test") {
      exec(http("request_1")
        .get("http://services.groupkt.com/country/get/all")
        .check(status.is(200)))

        .exec(http("request 2")
          .get("http://services.groupkt.com/country/get/iso2code/IN")
          .check(status.is(200)))
    }

  setUp(scn.inject(atOnceUsers(10)).protocols(httpConf))

}
