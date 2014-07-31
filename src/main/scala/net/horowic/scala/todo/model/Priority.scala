package net.horowic.scala.todo.model

import scala.slick.driver.PostgresDriver.simple._
import scala.Option

/**
 * User: mhorowic
 * Date: 23.03.14 19:10
 */
sealed case class Priority(id: Option[Int], name: String)

class Priorities(tag: Tag) extends Table[Priority](tag, "priority") {
  def id = column[Option[Int]]("priority_id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("name")

  def * = (id, name) <>(Priority.tupled, Priority.unapply)
}
