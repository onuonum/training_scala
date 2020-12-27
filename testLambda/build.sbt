import Dependencies._

name := "testLambda"
version := "0.1"
scalaVersion := "2.13.4"

assemblyMergeStrategy in assembly := {
  case "module-info.class" => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

lazy val tetetest = (project in file("./testLambda/tetetest"))
  .settings(name := "tetetest")
  .settings(libraryDependencies ++= lambdaDependencies ++ lambdaEventDependencies)

lazy val root = (project in file("."))
    .aggregate(
      presentation,
      consumer,
      domain,
      infrastructure,
      service
    )
  .enablePlugins(PlayScala)

lazy val presentation = (project in file("./presentation"))
  .aggregate(presentationCore)

lazy val presentationCore = (project in file("./presentation/core"))
  .dependsOn(domain)
  .settings(libraryDependencies ++= lambdaDependencies ++ lambdaEventDependencies)

lazy val consumer = (project in file("./presentation/consumer"))
  .aggregate(presentationCore, qiitateam2growi)

lazy val qiitateam2growi = (project in file("./presentation/consumer/qiitateam2growi"))
  .dependsOn(presentationCore, domain, service)
  .settings(libraryDependencies ++= lambdaDependencies ++ lambdaEventDependencies)

lazy val domain = (project in file("./domain"))
  .settings(libraryDependencies ++= circeDependencies )

lazy val infrastructure = (project in file("./infrastructure"))

lazy val service = (project in file("./service"))
  .dependsOn(domain)
  .settings(libraryDependencies ++= scalajHttpDependencies)
