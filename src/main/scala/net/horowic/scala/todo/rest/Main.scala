package net.horowic.scala.todo.rest

import spray.routing.SimpleRoutingApp
import akka.actor.ActorSystem
import spray.http.{StatusCodes, MediaTypes}
import spray.json.DefaultJsonProtocol
import spray.httpx.SprayJsonSupport
import net.horowic.scala.todo.model.{PriorityDaoImpl, PriorityService, Priority, PriorityServiceImpl}

/**
 * User: mhorowic
 * Date: 17.03.14 20:47
 */

object Main extends App with SimpleRoutingApp with DefaultJsonProtocol with SprayJsonSupport with PriorityService with PriorityDaoImpl{

  implicit val system = ActorSystem("my-system")
  implicit val format = jsonFormat2(Priority)

  startServer(interface = "localhost", port = 8080) {
    path("readAll") {
      get {
        respondWithMediaType(MediaTypes.`application/json`) {
          complete {
            readAll()
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
              //response: {"id":0, "name": "nazwa"}
              update(priority)
              StatusCodes.OK
            }
        }
      }~
      path("create") {
        entity(as[Priority]) {
          priority =>
            complete {
              //request with conent type application/json: { "name": "nazwa"}
              create(priority.name)
              StatusCodes.OK
            }
        }
      }
  }
}

