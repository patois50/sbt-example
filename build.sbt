import Dependencies._
import com.typesafe.sbt.packager.docker.Cmd

enablePlugins(DockerPlugin)

lazy val commonSettings = Seq(
  organization := "com.patrickmcgeever",
  version := "0.0.1-SNAPSHOT",
  scalaVersion := "2.12.4"
)

maintainer in Docker := "Patrick"

dockerExposedPorts := Seq(8080)
//defaultLinuxInstallLocation in Docker := "/opt/hello-world"
dockerExecCommand := Seq("/opt/hello-world/start.sh")


dockerCommands := Seq()
dockerCommands := Seq(
  Cmd("FROM", "8u151-jre-alpine3.7"),
  Cmd("LABEL", s"""MAINTAINER="${maintainer.value}""""),
  Cmd("PORTS", "8080"),
  Cmd("WORKDIR", "/opt/hello-world/")
)

mappings in Docker += baseDirectory.value / "bin" / "start.sh" -> "bin/start.sh"


lazy val service = project
  .settings(
    name := "helloWorld",
    commonSettings,
    libraryDependencies ++= serviceDeps,
    mainClass in assembly := Some("com.patrickmcgeever.helloworld.HelloWorld")
  )
