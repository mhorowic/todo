package net.horowic.scala.todo.component

import net.horowic.scala.todo.component.service.impl.PriorityServiceComponentImpl
import net.horowic.scala.todo.component.repository.impl.{TaskRepositoryComponentImpl, PriorityRepositoryComponentImpl}
import net.horowic.scala.todo.component.service.impl.TaskServiceComponentImpl

/**
 * User: mhorowic
 * Date: 31.07.14 23:36
 */
object Configuration {
  val priority = new PriorityServiceComponentImpl with PriorityRepositoryComponentImpl
  val task = new TaskServiceComponentImpl with TaskRepositoryComponentImpl
  val priorityService = priority.priorityService
  val taskService = task.taskRepository
}
