package net.horowic.scala.todo.model

import scala.slick.driver.PostgresDriver.simple._
import scala.Option

/**
 * User: mhorowic
 * Date: 23.03.14 19:10
 */
case class Priority(id: Option[Int], name: String)

class Priorities(tag: Tag) extends Table[Priority](tag, "priority") {
  def id = column[Option[Int]]("priority_id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("name")

  def * = (id, name) <> (Priority.tupled, Priority.unapply)
}

trait PriorityDao {
  var priorities = TableQuery[Priorities]

  def create(name: String): Int

  def readAll(): List[Priority]

  def update(entity: Priority): Unit
}

trait PriorityDaoImpl extends PriorityDao {
  val db = Database.forURL("jdbc:postgresql:todo", driver = "org.postgresql.Driver", user = "mhorowic", password = "nbuser")

  override def create(name: String): Int = {
    db withSession {
      implicit session =>
        val id = (priorities returning priorities.map(_.id)) +=Priority(None, name)
        id.get
    }
  }

  override def readAll(): List[Priority] = {
    db withSession {
      implicit session =>
        priorities.list
    }
  }

  override def update(entity: Priority): Unit = {
    db withSession {
      implicit session =>
        priorities.filter(_.id === entity.id.get).update(entity)
    }
  }
}

trait PriorityService {
  this: PriorityDao =>
  def readAll(): List[Priority] = readAll()

  def create(name: String): Int = create(name)

  def update(entity: Priority) : Unit = update(entity)
}

class PriorityServiceImpl extends PriorityService with PriorityDaoImpl {

}
