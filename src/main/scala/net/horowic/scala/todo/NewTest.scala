package net.horowic.scala.todo

import net.horowic.scala.todo.model.PriorityServiceImpl

/**
 * User: mhorowic
 * Date: 26.03.14 23:04
 */
object NewTest {

  def main(args: Array[String]) = {
    val priorityService = new PriorityServiceImpl

    println(priorityService.readAll())

  }
}
