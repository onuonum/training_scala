package qiitateam2growi

import com.amazonaws.services.lambda.runtime.events.SQSEvent
import com.amazonaws.services.lambda.runtime.events.SQSEvent.SQSMessage
import giitateam2growi.Base
import org.scalatest.GivenWhenThen
import org.scalatest.wordspec.AnyWordSpec

import scala.collection.JavaConverters._

class BaseSpec extends AnyWordSpec with GivenWhenThen {
  "ど正常パターン" should {
    "パースできる" in new WithFixture {
      Given("リクエストが渡される")
      private val dummySqsEvent = createDummySqsEvent(
        s"""{
           |"event": {},
           |"data": {
           |          "id":"some_id",
           |          "uuid":"sim_uuid",
           |          "user":{
           |            "id":"some_id",
           |            "url_name":"name",
           |            "profile_image_url":"url"
           |          },
           |          "title":"title",
           |          "created_at":"2014-11-07 18:29:28 +0900","updated_at":"2014-11-07 22:29:28 +0900",
           |          "created_at_in_words":"7日",
           |          "updated_at_in_words":"7日",
           |          "tags":[
           |            {
           |              "name":"ruby",
           |              "url_name":"ruby",
           |              "icon_url":"icons/medium/missing.png",
           |              "versions":[]
           |            }
           |           ],
           |          "stock_count":0,
           |          "comment_count":0,
           |          "url":"some_url",
           |          "created_at_as_seconds":9999999999,
           |          "lgtm_count":0,
           |          "private":false,
           |          "coediting":false,
           |          "raw_body":"raw body",
           |          "body":"body",
           |          "stock_users":[],
           |          "comments":[]
           |        }
           |}""".stripMargin)
      Then("パースに成功する")
      private val actual = parseRequest(dummySqsEvent.getRecords.asScala.toSeq.head.getBody)
      println(actual)
    }
  }

  trait WithFixture extends Base {
    protected def createDummySqsEvent(body: String): SQSEvent = {
      val dummySqsEvent = new SQSEvent()
      val dummyMessage = new SQSMessage()
      dummyMessage.setMessageId("DUMMY_MESSAGE_ID")
      dummyMessage.setReceiptHandle("DUMMY_RECEIPT_HANDLE")
      dummyMessage.setBody(body)
      dummyMessage.setMd5OfBody("")
      dummyMessage.setAwsRegion("ap-northeast-1")
      dummyMessage.setEventSource("aws:sqs")
      dummyMessage.setEventSourceArn("")

      dummySqsEvent.setRecords(Seq(dummyMessage).asJava)
      dummySqsEvent
    }
  }

}
