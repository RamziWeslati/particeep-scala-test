package com.particeep.test.async

import scala.concurrent.{Future, Await}

import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.duration._

/**
 * You have 2 webservices, we want to compute the sum of the 2 webservice call.
 *
 * You need to write only the compute function.
 * For instance : compute(1) should return 1099 (1098 + 1)
 *
 * It's part of the exercise to handle error cases
 */
object AsyncBasic {

  def compute(id: String): Either[String, Long] = {      //returning an Either to handle error cases in a FP manner
    val result1 = Webservice1.call(id)
    val result2 = Webservice2.call(id)

    val result = result1 flatMap {
      case Some(n) => result2 map {
        case Right(p) => Right(n.toLong + p)
        case Left(err) => Left(s"Got this error after calling Webservice2: '$err'")
      }
     case None => Future {Left("Webservice1 returned no valid value")}
    }
  Await.result(result, Duration.Inf) //we should return a future here, but the function
                                     //description has the requirement of returning a value
  } 
}

object Webservice1 {
  private[this] val result = Map(
    "1"  -> 1,
    "2"  -> 21,
    "5"  -> 4,
    "10" -> 1987
  )

  def call(id: String): Future[Option[Int]] = Future(result.get(id))
}

object Webservice2 {
  private[this] val result = Map(
    "1"  -> 1098,
    "3"  -> 218777,
    "9"  -> 434,
    "10" -> Int.MaxValue
  )

  def call(id: String): Future[Either[String, Int]] = Future {
    result.get(id) match {
      case Some(x) => Right(x)
      case None    => Left("No value")
    }
  }
}
