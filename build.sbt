import Dependencies._

lazy val commonSettings = Seq(
  organization := "com.patrickmcgeever",
  version := "0.0.1-SNAPSHOT",
  scalaVersion := "2.12.4"
)

lazy val dockerSettings = Seq(
  mappings in Docker += baseDirectory.value / "bin" / "start.sh" -> "start.sh",
  packageName in Docker := "hello-world",
  dockerBaseImage := "openjdk:8u151-jre-alpine3.7",
  dockerLabels := Map("maintaner" -> "patrick"),
  dockerExposedPorts := Seq(8080),
  defaultLinuxInstallLocation in Docker := "/opt/hello-world",
  dockerEntrypoint := Seq(),
  dockerCmd := Seq("./start.sh")
)

lazy val root = (project in file("."))
  .settings(name := "hello-world")
  .settings(commonSettings)
  .aggregate(service)

lazy val service = project
  .enablePlugins(DockerPlugin)
  .settings(
    name := "hello-world",
    commonSettings,
    dockerSettings,
    libraryDependencies ++= serviceDeps,
    mainClass in assembly := Some("com.patrickmcgeever.helloworld.HelloWorld"),
    // removes all jar mappings in universal and appends the fat jar
    mappings in Universal := {
      val universalMappings = (mappings in Universal).value
      val fatJar = (assembly in Compile).value

      // removing means filtering
      val filtered = universalMappings filter {
        case (_, fileName) => !fileName.startsWith(s"${name.value}-assembly")
      }
      filtered :+ fatJar -> ("lib/" + fatJar.getName)
    }
  )