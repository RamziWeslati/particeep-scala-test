package com.particeep.test.akka

import akka.actor.Actor

import scala.concurrent.Future
import scala.util.{ Failure, Success }
import scala.concurrent.ExecutionContext.Implicits.global

/**
 * Do you see anything that could lead to potential problems ?
 * This Actor presents introduces two potential problems:
 *  1 - Directly mutating the state as done in line `` renders this component to be 
 *    mutable and not referentially transparent.
 *  2 - Using Future.onComplete to handle asynchronous calls introduces the possibility of race
 *    conditions (as handleResponse might change common mutable variables or produce side effects) and
 *    the Future might get exectued on another thread. 
 * What would you do to fix it ?
 *  1 - Rather than directly mutating the internal state in the Actor, it is considered best practice
 *      to use context.become which changes the actor's behavior at runtime where we can pass it a 
 *      partial function with the new state. This function would be responsible for implementing the
 *      receive logic.
 *      receive should in that case be always calling the funtion with the initial state.
 *  2 - Rather than using Future.onComplete, we should instead be sending the result of the futur to
 *      actor at hand, using pipeTo(self)
 * Do not mind about the not implemented code
 */
class WhatsWrong3 extends Actor {

  var internalState = "internal state"

  def receive: Receive = {
    case "a query" => {
      val requestF: Future[String] = queryAsyncServer()
      requestF.onComplete {
        case Success(r) => handleResponse(r)
        case Failure(e) => e.printStackTrace()
      }
    }
  }

  def handleResponse(r: String) = ??? // mutate internal state

  def queryAsyncServer(): Future[String] = ???
}
