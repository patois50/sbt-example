import Dependencies._
import com.typesafe.sbt.SbtNativePackager.autoImport.NativePackagerHelper._
import com.typesafe.sbt.packager.docker.Cmd

lazy val commonSettings = Seq(
  organization := "com.patrickmcgeever",
  version := "0.0.1-SNAPSHOT",
  scalaVersion := "2.12.4"
)

lazy val dockerSettings = Seq(
  packageName in Docker := "hello-world",
  dockerBaseImage := "openjdk:8u151-jre-alpine3.7",
  dockerLabels := Map("maintaner" -> "patrick"),
  dockerExposedPorts := Seq(8080),
  defaultLinuxInstallLocation in Docker := "/opt/hello-world",
  dockerCommands += Cmd("RUN", "chmod -R +x bin"),
  dockerEntrypoint := Seq(),
  dockerCmd := Seq("/opt/hello-world/bin/start.sh")
)

lazy val root = (project in file("."))
  .settings(name := "hello-world")
  .aggregate(service)

lazy val service = project
  .enablePlugins(DockerPlugin)
  .settings(
    name := "hello-world",
    commonSettings,
    dockerSettings,
    libraryDependencies ++= serviceDeps,
    mainClass in assembly := Some("com.patrickmcgeever.helloworld.HelloWorld"),
    mappings in Universal := {
      val fatJar = (assembly in Compile).value
      val binMapping = directory(file("bin/"))
      binMapping :+ fatJar -> ("lib/" + "hello-world.jar")
    }
  )