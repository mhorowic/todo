package net.horowic.scala.todo.component.service.impl

import net.horowic.scala.todo.component.service.PriorityServiceComponent
import net.horowic.scala.todo.component.repository.PriorityRepositoryComponent
import net.horowic.scala.todo.model.Priority

/**
 * User: mhorowic
 * Date: 31.07.14 23:18
 */
trait PriorityServiceComponentImpl extends PriorityServiceComponent {
  this: PriorityRepositoryComponent =>

  def priorityService = new PriorityServiceComponentImpl

  class PriorityServiceComponentImpl extends PriorityService {
    def readAll(): List[Priority] = priorityRepository.readAll

    def create(priority: Priority): Int = priorityRepository.create(priority)

    def delete(priorityId: Int): Unit = priorityRepository.delete(priorityId)

    def update(priority: Priority): Unit = priorityRepository.update(priority)

    def findById(priorityId: Option[Int]): Priority = priorityRepository.findById(priorityId)
  }

}
