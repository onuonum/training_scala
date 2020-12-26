import sbt._

object Dependencies {

  //scala test
  private lazy val scalaTestVersion = "3.2.3"
  private lazy val scalaMockVersion = "4.4.0"
  private lazy val testDependencies = Seq(
    "org.scalatest" %% "scalatest" % "3.1.2" % Test,
    "org.scalatest" %% "scalatest-wordspec" % scalaTestVersion % Test,
    "org.scalamock" %% "scalamock" % scalaMockVersion % Test
  )

  //circe
  private lazy val circeVersion = "0.12.3"
  lazy val circeDependencies = Seq(
    "io.circe" %% "circe-core" % circeVersion,
    "io.circe" %% "circe-generic" % circeVersion,
    "io.circe" %% "circe-parser" % circeVersion,
  )  ++ testDependencies

  //aws sdk
  private lazy val awsSdkV2Version = "2.15.45"
  lazy val lambdaDependencies = Seq(
    "com.amazonaws" % "aws-lambda-java-core" % "1.2.1"
  ) ++ testDependencies
  lazy val lambdaEventDependencies = Seq(
    "com.amazonaws" % "aws-lambda-java-events" % "3.6.0"
  ) ++ testDependencies
  lazy val sqsDependencies = Seq(
    "software.amazon.awssdk" % "sqs" % awsSdkV2Version
  ) ++ testDependencies


}
