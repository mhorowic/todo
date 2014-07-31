package net.horowic.scala.todo.component.repository.impl

import net.horowic.scala.todo.component.repository.PriorityRepositoryComponent
import net.horowic.scala.todo.model.Priority
import scala.slick.driver.PostgresDriver.simple._

/**
 * User: mhorowic
 * Date: 31.07.14 22:03
 */
trait PriorityRepositoryComponentImpl extends PriorityRepositoryComponent {
  def priorityRepository = new PriorityRepositoryImpl

  class PriorityRepositoryImpl extends PriorityRepository {

    val db = Database.forURL("jdbc:postgresql:todo", driver = "org.postgresql.Driver", user = "mhorowic", password = "nbuser")

    def readAll(): List[Priority] = {
      db withSession {
        implicit session =>
          priorities.list
      }
    }

    def create(priority: Priority): Int = {
      db withSession {
        implicit session =>
          val id = (priorities returning priorities.map(_.id)) += priority
          id.get
      }
    }

    def delete(priorityId: Int): Unit = {
      db withSession {
        implicit session =>
          priorities where (_.id === priorityId) delete
      }
    }

    def update(priority: Priority): Unit = {
      db withSession {
        implicit session =>
          priorities where (_.id === priority.id) update (priority)
      }
    }

    def findById(priorityId: Option[Int]): Priority = {
      db withSession {
        implicit session =>
          val findById = priorities.findBy(_.id)
          findById(priorityId) first
      }
    }
  }

}
