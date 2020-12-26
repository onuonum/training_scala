package giitateam2growi

import com.amazonaws.services.lambda.runtime.events.SQSEvent
import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}
import qiitateam2growi.{SqsMessage, MessageProtocol}
import io.circe.parser.decode

import collection.JavaConverters._
trait Base extends RequestHandler[SQSEvent, Seq[String]] {

  def parseRequest(input: String): Either[Exception, MessageProtocol] =
    decode[SqsMessage](input).fold(
      e => Left(e),
      v => Right(v.data)
    )

  override def handleRequest(input: SQSEvent, context: Context): Seq[String] =
  /*
  1. inputをパースする
  2. apiを発行できる形に整形する
  3. api発行
   */
    (for {
      p <- parseRequest(input.getRecords.asScala.toSeq.head.getBody)
    } yield  p.body).fold(
      e => Seq(e.toString),
      v => Seq(v)
    )

}
