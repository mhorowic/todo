package net.horowic.scala.todo

import net.horowic.scala.todo.model.{PriorityDaoImpl, PriorityService}

/**
 * User: mhorowic
 * Date: 23.03.14 19:30
 */
object MainTest extends PriorityService with PriorityDaoImpl {
  def main(args: Array[String]) {
    println(readAll())

    println(create("test"))
    println(readAll())

    //update(15, "abcde")
    //println(readAll())

  }
}
