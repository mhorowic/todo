package net.horowic.scala.todo.component.service

import net.horowic.scala.todo.model.Priority

/**
 * User: mhorowic
 * Date: 31.07.14 23:15
 */
trait PriorityServiceComponent {
  def priorityService: PriorityService

  trait PriorityService {
    def readAll(): List[Priority]

    def create(priority: Priority): Int

    def delete(priorityId: Int): Unit

    def update(priority: Priority): Unit

    def findById(priorityId: Option[Int]): Priority
  }

}
