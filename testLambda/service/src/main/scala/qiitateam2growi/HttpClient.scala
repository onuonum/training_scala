package qiitateam2growi

import scalaj.http._

class HttpClient extends commonHttpClient {

  def Post(url: String, headers: Seq[(String, String)], requestBody: String): HttpResponse[String] =
    Http(url)
      .postData(requestBody)
      .headers(headers)
      .timeout(connTimeoutMs = connectTimeoutMillis, readTimeoutMs = readTimeoutMillis)
      .asString

}
