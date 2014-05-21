package net.horowic.scala.todo.test

/**
 * User: mhorowic
 * Date: 01.04.14 00:49
 */

object Solution {

  def solution(N: Int, A: Array[Int]): Array[Int] = {
    val result = Array.fill[Int](N)(0)
    var max: Int = 0

    for (j <- 0 until A.length; element = A(j)) {
      if (element <= N) {
        if(result(element - 1) < max)
        result(element - 1) = result(element - 1) + 1 + max
        else
          result(element - 1) = result(element - 1) + 1
      } else if (element == N + 1) {
        max = result.max
      }
    }

    for (i <- 0 until N) {
      if (result(i) != 0)
        result(i) = result(i) + max
      else
        result(i) = max
    }

    result
  }

  def main(args: Array[String]) = {
    val result = solution(5, Array[Int](3, 4, 4, 6, 1, 4, 4))
    result.foreach(element => (print(element + ", ")))
    println()
    println("3, 2, 2, 4, 2,")
  }

}