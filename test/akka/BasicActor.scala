package com.particeep.test.akka

import org.scalatest._
import org.scalatestplus.play.PlaySpec

import FireActor._

class BasicActorTest extends PlaySpec {
  "FireActor" should {
    "print 'Hello there.' and 'What?' when after calling fireActor" in {
      val out = new java.io.ByteArrayOutputStream()
      Console.withOut(out) {
        fireActor()
        Thread.sleep(1000)                  //waiting for stream to end, I am sure the is
                                              //a better way to do this
      }
      out.toString mustBe "Hello there.\nWhat?\n"
    }      
  }
}