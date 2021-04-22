package com.particeep.test.basic
import scala.annotation.tailrec

/**
 * This is basic language questions so don't use external library or build in function
 */
object BasicScala {

  /**
   * Encode parameter in url format
   *
   * Example:
   *
   * input  : Map("sort_by" -> "name", "order_by" -> "asc", "user_id" -> "12")
   * output : "?sort_by=name&order_by=asc&user_id=12"
   *
   * input  : Map()
   * output : ""
   */
  def encodeParamsInUrl(params: Map[String, String]): String = {
    /*this function dosen't necesserlly need to be executed in tail recursion as the urls are usually not that long
        and tail recursion would cause a lose of the stack call which doesn't really matter for this specific problem
    */
      @tailrec
      def appendParams(url: String, params: Map[String, String]): String = {
        params.headOption match {
          case Some((param1, param2)) => {
           appendParams(url + "&" + param1 + "=" + param2, params.tail)
         }
          case None => if (url == "") "" else "?" + url.tail        
        }
      }
      appendParams("", params)
  }

  /**
   * Test if a String is an email
   */
  def isEmail(maybeEmail: String): Boolean = """^[^@\s]+@[^@\s\.]+\.[^@\.\s]+$""".r.unapplySeq(maybeEmail).isDefined
  /**
   * Compute i ^ n
   *
   * Example:
   *
   * input : (i = 2, n = 3) we compute 2^3 = 2x2x2
   * output : 8
   *
   * input : (i = 99, n = 38997)
   * output : 1723793299              //is this output wrong ?
   */
  def power(i: Int, n: Int): Double = {           //tail recursion is necessary here as n might be as big as
    @tailrec                                       //maximum number of calls before raising a stackoverflow error
    def nMultiply(i: Int, n: Int, acc: Double): Double = {
      if (n == 0) {               //includes 0^0
        acc
      } else if (n > 0) {
        nMultiply(i, n - 1, acc * i)
      } else {                    // n < 0
        nMultiply(i, n + 1, acc / i)
      }
    }
    nMultiply(i, n, 1.0)
  }

}
