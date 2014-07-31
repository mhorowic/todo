package net.horowic.scala.todo

import net.horowic.scala.todo.component.Configuration
import net.horowic.scala.todo.model.Priority


/**
 * User: mhorowic
 * Date: 23.03.14 19:30
 */
object MainTest {
  def main(args: Array[String]) {
    val priorityService = Configuration.priorityService

    println(priorityService.readAll())

    priorityService.delete(23)

    println(priorityService.readAll())

    val id = priorityService.create(Priority(None, "Cake Test"))

    println(priorityService.readAll())

    priorityService.update(Priority(Some(id), "Cake_tst"))

    println(priorityService.readAll())

    println(priorityService.findById(Some(id)))

  }
}
