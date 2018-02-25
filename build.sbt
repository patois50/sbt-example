import Dependencies._

lazy val commonSettings = Seq(
  organization := "com.patrickmcgeever",
  version := "0.0.1-SNAPSHOT",
  scalaVersion := "2.12.4"
)

lazy val root = (project in file("."))
  .aggregate(service)
  .settings(
    name := "helloWorldRoot",
    commonSettings,
  )

lazy val service = project
  .settings(
    name := "helloWorld",
    commonSettings,
    libraryDependencies ++= serviceDeps
  )
