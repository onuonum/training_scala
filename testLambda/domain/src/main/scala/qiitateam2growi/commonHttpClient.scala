package qiitateam2growi

import scalaj.http.HttpResponse

trait commonHttpClient {
  val readTimeoutMillis = 5000
  var connectTimeoutMillis = 5000

  def Post(url: String, headers: Seq[(String, String)], requestBody: String): HttpResponse[String]
}

