package net.horowic.scala.todo.rest

import spray.routing.SimpleRoutingApp
import akka.actor.ActorSystem
import spray.http.{StatusCodes, MediaTypes}
import spray.json._
import spray.httpx.SprayJsonSupport
import net.horowic.scala.todo.component.Configuration
import java.sql.Date
import net.horowic.scala.todo.model.Priority
import net.horowic.scala.todo.model.Task
import scala.Some

/**
 * User: mhorowic
 * Date: 17.03.14 20:47
 */

object Main extends App with SimpleRoutingApp with DefaultJsonProtocol with SprayJsonSupport {

  implicit val system = ActorSystem("my-system")
  implicit val priorityFormat = jsonFormat2(Priority)

  implicit object DateJsonFormat extends JsonFormat[Date] {
    def write(date: Date) = JsString(date.toString)

    def read(value: JsValue) = value match {
      case JsString(date) => Date.valueOf(date)
      case x => throw new DeserializationException("Expected Date as JsString, but got " + x)
    }
  }

  implicit val taskFormat = jsonFormat5(Task)


  val priorityService = Configuration.priorityService
  val taskService = Configuration.taskService

  startServer(interface = "localhost", port = 8080) {
    pathPrefix("Todo") {
      pathPrefix("priorities") {
        path("") {
          get {
            respondWithMediaType(MediaTypes.`application/json`) {
              complete {
                priorityService.readAll()
              }
            }
          } ~
            put {
              entity(as[Priority]) {
                priority =>
                  complete {
                    priorityService.update(priority)
                    StatusCodes.OK
                  }
              }
            } ~
            post {
              entity(as[Priority]) {
                priority =>
                  complete {
                    priorityService.create(priority)
                    StatusCodes.OK
                  }
              }
            }
        } ~
          path(IntNumber) {
            id =>
              get {
                respondWithMediaType(MediaTypes.`application/json`) {
                  complete {
                    priorityService.findById(Some(id))
                  }
                }
              } ~
                delete {
                  complete {
                    priorityService.delete(id)
                    StatusCodes.OK
                  }
                }
          }
      } ~
        pathPrefix("tasks") {
          path("") {
            get {
              respondWithMediaType(MediaTypes.`application/json`) {
                complete {
                  taskService.readAll()
                }
              }
            } ~
              put {
                entity(as[Task]) {
                  task =>
                    complete {
                      taskService.update(task)
                      StatusCodes.OK
                    }
                }
              } ~
              post {
                entity(as[Task]) {
                  task =>
                    complete {
                      taskService.create(task)
                      StatusCodes.OK
                    }
                }
              }
          } ~
            path(IntNumber) {
              id =>
                get {
                  respondWithMediaType(MediaTypes.`application/json`) {
                    complete {
                      taskService.findById(Some(id))
                    }
                  }
                } ~
                  delete {
                    complete {
                      Thread.sleep(1500);
                      taskService.delete(id) match {
                        case 0 => StatusCodes.InternalServerError
                        case 1 => StatusCodes.OK
                      }
                    }
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



