import sbt._

object Dependencies {
  val akkaHttp = "com.typesafe.akka" %% "akka-http" % "10.1.0-RC2"
  val akkaStream = "com.typesafe.akka" %% "akka-stream" % "2.5.9"

  val serviceDeps = Seq(
    akkaHttp,
    akkaStream
  )
}
