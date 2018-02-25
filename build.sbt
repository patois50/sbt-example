import Dependencies._

lazy val commonSettings = Seq(
  organization := "com.patrickmcgeever",
  version := "0.0.1-SNAPSHOT",
  scalaVersion := "2.12.4"
)


lazy val service = project
  .settings(
    name := "helloWorld",
    commonSettings,
    libraryDependencies ++= serviceDeps,
    mainClass in assembly := Some("com.patrickmcgeever.helloworld.HelloWorld"),
  )
