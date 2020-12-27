package qiitateam2growi

import org.scalatest.GivenWhenThen
import org.scalatest.wordspec.AnyWordSpec

/*
参考
https://gist.github.com/pandanote-info/599d885a3af95e28c8945d2a796f0a6e
 */
class HttpClientSpec extends AnyWordSpec with GivenWhenThen {

  "ど正常パターン" should {
    "postを投げることができる" in new WithFixture {
      Given("リクエストが渡される")
      private val url = "http://localhost:3000/_api/v3/pages/create"
      private val headers = Seq(
        ("content-type", "application/json"),
        ("Authorization", "OWIj1AI6UhY2v6M0hzifR2x6Xt0lhjd8PTZ6G623+/o=")
      )
      private val body =
        s"""{
           |  "body": {
           |     "id":"some_id",
           |     "uuid":"sim_uuid",
           |     "user":{
           |       "id":"some_id",
           |       "url_name":"name",
           |       "profile_image_url":"url"
           |     },
           |     "title":"title",
           |     "created_at":"2014-11-07 18:29:28 +0900","updated_at":"2014-11-07 22:29:28 +0900",
           |     "created_at_in_words":"7日",
           |     "updated_at_in_words":"7日",
           |     "tags":[
           |       {
           |         "name":"ruby",
           |         "url_name":"ruby",
           |         "icon_url":"icons/medium/missing.png",
           |         "versions":[]
           |       }
           |      ],
           |     "stock_count":0,
           |     "comment_count":0,
           |     "url":"some_url",
           |     "created_at_as_seconds":9999999999,
           |     "lgtm_count":0,
           |     "private":false,
           |     "coediting":false,
           |     "raw_body":"raw body",
           |     "body":"body",
           |     "stock_users":[],
           |     "comments":[]
           |   },
           |   "path": "/"
           |}
           |""".stripMargin

      Then("postに成功する")
      private val actual = Post(url, headers, body)
      println(actual.code)
    }
  }

  trait WithFixture extends HttpClient {
  }

}
