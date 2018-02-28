package com.patrickmcgeever.helloworld

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.stream.ActorMaterializer

import scala.io.StdIn
import akka.http.scaladsl.server.Directives._

import scala.concurrent.ExecutionContextExecutor

object HelloWorld {

  def main(args: Array[String]) {

    implicit val system: ActorSystem = ActorSystem("my-system")
    implicit val materializer: ActorMaterializer = ActorMaterializer()

    val route =
      path("hello") {
        get {
          complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "<h1>Say hello to akka-http</h1>"))
        }
      }

    Http().bindAndHandle(route, "0.0.0.0", 8080)
    println(s"Server running on port 8080")
  }

}
