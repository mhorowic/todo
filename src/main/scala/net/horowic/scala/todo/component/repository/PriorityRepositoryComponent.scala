package net.horowic.scala.todo.component.repository

import net.horowic.scala.todo.model.{Priorities, Priority}
import scala.slick.lifted.TableQuery

/**
 * User: mhorowic
 * Date: 31.07.14 20:18
 */
trait PriorityRepositoryComponent {
  def priorityRepository: PriorityRepository

  trait PriorityRepository {

    val priorities = TableQuery[Priorities]

    def readAll(): List[Priority]

    def create(priority: Priority): Int

    def delete(priorityId: Int): Unit

    def update(priority: Priority): Unit

    def findById(priorityId: Option[Int]): Priority
  }

}
