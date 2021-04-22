package com.particeep.test.basic
import scala.annotation.tailrec

/**
 * Compute the avarage of the list
 *
 * ex : [1, 10, 16] -> 9
 */
object ComputeAvarage {

  def average(l: List[Double]): Option[Double] = {
  	@tailrec
  	def sumHead(l: List[Double], acc: Double): Double = {
      //returns the sum of a list of doubles
  		l.headOption match {
  			case Some(head) => sumHead(l.tail, head + acc)
  			case _ => acc
  		}
  	}
  	val sum = sumHead(l, 0)
  	if (sum != 0)
  		Some(sum / l.length)
  	else
  		None
  }

}
