package com.particeep.test.akka

import org.scalatest._
import org.scalatestplus.play.PlaySpec
import org.scalatest.funspec._

class BasicStreamTest extends PlaySpec {
  "BasicStream " should {
    "stream all even numbers between 1 and 1000" in {
      val stream = new java.io.ByteArrayOutputStream()
	  Console.withOut(stream) {
	     BasicStream.main(Array[String]())
	     Thread.sleep(2000)							//waiting for stream to end, I am sure the is
	     											  //a better way to do this
  	  }
  	  val streamedNumbers = stream.toString.split("\n")
	  streamedNumbers.length mustBe 500
	  streamedNumbers.forall(_.toInt % 2 == 0) mustBe true

	}
  }
}