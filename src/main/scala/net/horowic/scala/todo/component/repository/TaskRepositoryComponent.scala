package net.horowic.scala.todo.component.repository

import net.horowic.scala.todo.model.{Tasks, Task}
import scala.slick.lifted.TableQuery

/**
 * User: mhorowic
 * Date: 31.07.14 20:24
 */
trait TaskRepositoryComponent {
  def taskRepository: TaskRepository

  trait TaskRepository {

    val tasks = TableQuery[Tasks]

    def readAll(): List[Task]

    def create(task: Task): Int

    def delete(taskId: Int): Int

    def update(task: Task): Unit

    def findById(taskId: Option[Int]): Task
  }

}
