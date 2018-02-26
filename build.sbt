import Dependencies._

enablePlugins(DockerPlugin)

lazy val commonSettings = Seq(
  organization := "com.patrickmcgeever",
  version := "0.0.1-SNAPSHOT",
  scalaVersion := "2.12.4"
)

lazy val dockerSettings = Seq(
  dockerBaseImage := "8u151-jre-alpine3.7",
  maintainer in Docker := "Patrick",
  dockerExposedPorts := Seq(8080),
  defaultLinuxInstallLocation in Docker := "/opt/hello-world",
  dockerExecCommand := Seq("/opt/hello-world/start.sh"),
  mappings in Docker
)

lazy val service = project
  .settings(
    name := "helloWorld",
    commonSettings,
    libraryDependencies ++= serviceDeps,
    mainClass in assembly := Some("com.patrickmcgeever.helloworld.HelloWorld"),
    dockerSettings
  )
