package net.horowic.scala.todo

import scala.slick.driver.PostgresDriver.simple._
import java.sql.Date


class Priority(tag: Tag) extends Table[(Option[Int], String)](tag, "priority") {
  def id = column[Option[Int]]("priority_id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("name")

  def * = (id, name)
}

object Hello extends App {

  var priorities = TableQuery[Priority]

  class Task(tag: Tag) extends Table[(Option[Int], String, String, Date, Option[Int])](tag, "task") {
    def id = column[Option[Int]]("task_id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def description = column[String]("description")

    def dueDate = column[Date]("due_date")

    def priorityId = column[Option[Int]]("priority_id")

    def * = (id, name, description, dueDate, priorityId)

    def priority = foreignKey("tasks_priority_id_fkey", priorityId, priorities)(_.id)
  }

  var tasks = TableQuery[Task]

   //addPriority("external")

  println(getPriorities())


  def getPriorities() : List[(Option[Int], String)] = {
    Database.forURL("jdbc:postgresql:todo", driver = "org.postgresql.Driver", user = "mhorowic", password = "nbuser") withSession {
      implicit session =>
    priorities.list
    }
  }

  def addPriority(name: String) = {
    Database.forURL("jdbc:postgresql:todo", driver = "org.postgresql.Driver", user = "mhorowic", password = "nbuser") withSession {
      implicit session =>
        priorities += (None, name)
    }
  }
}