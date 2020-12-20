import com.amazonaws.services.lambda.runtime.{Context, RequestHandler}

trait Base extends RequestHandler[Map[String,String],String]{
  override def handleRequest(input: Map[String,String], context: Context): String = {
    println("hello")
    "hello"
  }

}
