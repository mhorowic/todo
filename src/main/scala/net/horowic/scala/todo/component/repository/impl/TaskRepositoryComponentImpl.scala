package net.horowic.scala.todo.component.repository.impl

import net.horowic.scala.todo.component.repository.TaskRepositoryComponent
import net.horowic.scala.todo.model.Task
import scala.slick.driver.PostgresDriver.simple._

/**
 * User: mhorowic
 * Date: 31.07.14 22:57
 */
trait TaskRepositoryComponentImpl extends TaskRepositoryComponent {
  def taskRepository = new TaskRepositoryImpl

  class TaskRepositoryImpl extends TaskRepository {

    val db = Database.forURL("jdbc:postgresql:todo", driver = "org.postgresql.Driver", user = "mhorowic", password = "nbuser")

    def readAll(): List[Task] = {
      db withSession {
        implicit session =>
          tasks.list
      }
    }

    def create(task: Task): Int = {
      db withSession {
        implicit session =>
          val id = (tasks returning tasks.map(_.id)) += task
          id.get
      }
    }

    def delete(taskId: Int): Int = {
      db withSession {
        implicit session =>
          tasks where (_.id === taskId) delete
      }
    }

    def update(task: Task): Unit = {
      db withSession {
        implicit session =>
          tasks where (_.id === task.id) update (task)
      }
    }

    def findById(taskId: Option[Int]): Task = {
      db withSession {
        implicit session =>
          val findById = tasks.findBy(_.id)
          findById(taskId) first
      }
    }
  }

}
