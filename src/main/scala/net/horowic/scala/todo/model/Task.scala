package net.horowic.scala.todo.model

import java.sql.Date

import scala.slick.driver.PostgresDriver.simple._
import scala.Option


/**
 * User: mhorowic
 * Date: 23.03.14 23:38
 */

sealed case class Task(id: Option[Int], name: String, description: String, dueDate: Date, priorityId: Option[Int])

class Tasks(tag: Tag) extends Table[Task](tag, "task") {
  var priorities = TableQuery[Priorities]

  def id = column[Option[Int]]("task_id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("name")

  def description = column[String]("description")

  def dueDate = column[Date]("due_date")

  def priorityId = column[Option[Int]]("priority_id")

  def * = (id, name, description, dueDate, priorityId) <>(Task.tupled, Task.unapply)

  def priority = foreignKey("tasks_priority_id_fkey", priorityId, priorities)(_.id)
}
