import Dependencies._

name := "testLambda"

version := "0.1"

scalaVersion := "2.13.4"

lazy val root = (project in file("./testLambda/tetetest"))
  .settings(name := "tetetest")
  .settings(libraryDependencies ++= lambdaSettings)

