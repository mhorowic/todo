package net.horowic.scala.todo.component.service

import net.horowic.scala.todo.model.Task

/**
 * User: mhorowic
 * Date: 31.07.14 23:44
 */
trait TaskServiceComponent {
  def taskService: TaskService

  trait TaskService {
    def readAll(): List[Task]

    def create(task: Task): Int

    def delete(taskId: Int): Int

    def update(task: Task): Unit

    def findById(taskId: Option[Int]): Task
  }

}
