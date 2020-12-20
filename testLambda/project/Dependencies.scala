import sbt._

object Dependencies {

  lazy val lambdaSettings = Seq(
    "com.amazonaws" % "aws-lambda-java-core" % "1.0.0",
    "com.amazonaws" % "aws-lambda-java-events" % "1.1.0",
  )
}
