package net.horowic.scala.todo.rest

import spray.routing.SimpleRoutingApp
import akka.actor.ActorSystem
import spray.http.{StatusCodes, MediaTypes}
import spray.json.DefaultJsonProtocol
import spray.httpx.SprayJsonSupport
import net.horowic.scala.todo.model.Priority
import net.horowic.scala.todo.component.Configuration

/**
 * User: mhorowic
 * Date: 17.03.14 20:47
 */

object Main extends App with SimpleRoutingApp with DefaultJsonProtocol with SprayJsonSupport {

  implicit val system = ActorSystem("my-system")
  implicit val format = jsonFormat2(Priority)
  val prioritiesService = Configuration.priorityService

  startServer(interface = "localhost", port = 8080) {
    pathPrefix("Todo") {
      path("priorities") {
        get {
          respondWithMediaType(MediaTypes.`application/json`) {
            complete {
              prioritiesService.readAll()
            }
          }
        }
      } ~
        path("shutdown") {
          get {
            complete {
              system.shutdown()
              "exit"
            }
          }
        } ~
        path("update") {
          entity(as[Priority]) {
            priority =>
              complete {
                //request with content type application/json: {"id":0, "name": "nazwa"}
                prioritiesService.update(priority)
                StatusCodes.OK
              }
          }
        } ~
        path("create") {
          entity(as[Priority]) {
            priority =>
              complete {
                //request with content type application/json: { "name": "nazwa"}
                prioritiesService.create(priority)
                StatusCodes.OK
              }
          }
        } ~
        path("") {
          getFromResource("web/index.html")
        } ~ {
        getFromResourceDirectory("web")
      }
    }
  }
}

