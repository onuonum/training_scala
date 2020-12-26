import Dependencies._

name := "testLambda"

version := "0.1"

scalaVersion := "2.13.4"

lazy val tetetest = (project in file("./testLambda/tetetest"))
  .settings(name := "tetetest")
  .settings(libraryDependencies ++= lambdaDependencies ++ lambdaEventDependencies)

lazy val root = (project in file("."))
    .aggregate(
      presentation,
      consumer,
      domain,
      infrastrucrure,
    )

assemblyMergeStrategy in assembly := {
  case "module-info.class" => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

lazy val presentation = (project in file("./presentation"))
  .aggregate(presentationCore)

lazy val presentationCore = (project in file("./presentation/core"))
  .dependsOn(domain)
  .settings(libraryDependencies ++= lambdaDependencies ++ lambdaEventDependencies)

lazy val consumer = (project in file("./presentation/consumer"))
  .aggregate(presentationCore, qiitateam2growi)

lazy val qiitateam2growi = (project in file("./presentation/consumer/qiitateam2growi"))
  .dependsOn(presentationCore, domain, infraSQS)
  .settings(libraryDependencies ++= lambdaDependencies ++ lambdaEventDependencies)

lazy val domain = (project in file("./domain"))
  .settings(libraryDependencies ++= circeDependencies)

lazy val infrastrucrure = (project in file("./infrastrucrure"))
  .aggregate(infraSQS)

lazy val infraSQS = (project in file("./infrastrucrure/sqs"))
  .dependsOn(domain)
  .settings(libraryDependencies ++=sqsDependencies )

