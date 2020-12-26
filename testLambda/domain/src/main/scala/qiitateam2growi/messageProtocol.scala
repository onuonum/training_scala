package qiitateam2growi

import io.circe.{Decoder, HCursor, JsonObject}

case class User(
                 id: String,
                 url_name: String,
                 profile_image_url: String,
               )

object User {
  implicit val decoder: Decoder[User] = (c: HCursor) => {
    for {
      id <- c.downField("id").as[String]
      url_name <- c.downField("url_name").as[String]
      profile_image_url <- c.downField("profile_image_url").as[String]
    } yield new User(
      id = id,
      url_name = url_name,
      profile_image_url = profile_image_url,
    )
  }
}

case class Tag(
                name: String,
                url_name: String,
                icon_url: String,
                versions: Seq[Option[String]] //???
              )

object Tag {
  implicit val decoder: Decoder[Tag] = (c: HCursor) => {
    for {
      name <- c.downField("name").as[String]
      url_name <- c.downField("url_name").as[String]
      icon_url <- c.downField("icon_url").as[String]
      versions <- c.downField("versions").downArray.as[Option[String]]
    } yield new Tag(
      name = name,
      url_name = url_name,
      icon_url = icon_url,
      versions = Seq(versions),
    )
  }
}

//qiitateamからのエクスポート結果を格納する
case class MessageProtocol(
                            id: String,
                            uuid: String,
                            user: User,
                            title: String,
                            created_at: String, //"2014-11-07 18:29:28 +0900",
                            updated_at: String, //"2014-11-07 22:29:28 +0900",
                            created_at_in_words: String,
                            updated_at_in_words: String,
                            tags: Seq[Tag],
                            stock_count: Long,
                            comment_count: Long,
                            url: String,
                            created_at_as_seconds: Long,
                            lgtm_count: Long,
                            `private`: Boolean,
                            coediting: Boolean,
                            raw_body: String,
                            body: String,
                            stock_users: Seq[Option[String]],
                            comments: Seq[Option[String]]
                          )

object MessageProtocol {
  implicit val decoder: Decoder[MessageProtocol] = (c: HCursor) => {
    for {
      id <- c.downField("id").as[String]
      uuid <- c.downField("uuid").as[String]
      user <- c.downField("user").as[User]
      title <- c.downField("title").as[String]
      created_at <- c.downField("created_at").as[String]
      updated_at <- c.downField("updated_at").as[String]
      created_at_in_words <- c.downField("created_at_in_words").as[String]
      updated_at_in_words <- c.downField("updated_at_in_words").as[String]
      tags <- c.downField("tags").downArray.as[Tag]
      stock_count <- c.downField("stock_count").as[Long]
      comment_count <- c.downField("comment_count").as[Long]
      url <- c.downField("url").as[String]
      created_at_as_seconds <- c.downField("created_at_as_seconds").as[Long]
      lgtm_count <- c.downField("lgtm_count").as[Long]
      p <- c.downField("private").as[Boolean]
      coediting <- c.downField("coediting").as[Boolean]
      raw_body <- c.downField("raw_body").as[String]
      body <- c.downField("body").as[String]
      stock_users <- c.downField("stock_users").downArray.as[Option[String]]
      comments <- c.downField("comments").downArray.as[Option[String]]
    } yield new MessageProtocol(
      id = id,
      uuid = uuid,
      user = user,
      title = title,
      created_at: String, //"2014-11-07 18:29 = created_at: String, //"2014-11-07 18:29,
      updated_at: String, //"2014-11-07 22:29 = updated_at: String, //"2014-11-07 22:29,
      created_at_in_words = created_at_in_words,
      updated_at_in_words = updated_at_in_words,
      tags = Seq(tags),
      stock_count = stock_count,
      comment_count = comment_count,
      url = url,
      created_at_as_seconds = created_at_as_seconds,
      lgtm_count = lgtm_count,
      `private` = p,
      coediting = coediting,
      raw_body = raw_body,
      body = body,
      stock_users = Seq(stock_users),
      comments = Seq(comments),
    )
  }
}

case class SqsMessage (
                      event: JsonObject,
                      data: MessageProtocol
                      )
object SqsMessage {
  implicit val decoder: Decoder[SqsMessage] = (c: HCursor) => {
    for {
      event <- c.downField("event").as[JsonObject]
      data <- c.downField("data").as[MessageProtocol]
    } yield new SqsMessage(
      event = event,
      data = data
    )
  }
}
