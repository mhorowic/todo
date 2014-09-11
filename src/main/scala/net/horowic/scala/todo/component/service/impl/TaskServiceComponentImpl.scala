package net.horowic.scala.todo.component.service.impl

import net.horowic.scala.todo.component.service.TaskServiceComponent
import net.horowic.scala.todo.component.repository.TaskRepositoryComponent
import net.horowic.scala.todo.model.Task

/**
 * User: mhorowic
 * Date: 31.07.14 23:47
 */
trait TaskServiceComponentImpl extends TaskServiceComponent {
  this: TaskRepositoryComponent =>

  def taskService = new TaskServiceComponentImpl

  class TaskServiceComponentImpl extends TaskService {
    def findById(taskId: Option[Int]): Task = taskRepository.findById(taskId)

    def update(task: Task): Unit = taskRepository.update(task)

    def delete(taskId: Int): Int = taskRepository.delete(taskId)

    def create(task: Task): Int = taskRepository.create(task)

    def readAll(): List[Task] = taskRepository.readAll()
  }

}
