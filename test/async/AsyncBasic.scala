package com.particeep.test.async

import play.api.i18n.Lang

import org.scalatestplus.play.PlaySpec
import AsyncBasic._

class AsyncBasicTest extends PlaySpec {

  "AsyncBasic" should {
    "compute the result of two valid calls to webservices" in {
      compute("1") match {
        case Right(n) => n mustBe 1099
        case Left(e) => fail()
      }
    }
    "return a left with the error message that states Webservice1 failure when computing 3" in {
      compute("3") match {
        case Right(n) => fail
        case Left(e) => e mustBe "Webservice1 returned no valid value"
      }
    }
    "return a left with the error message that states Webservice1's failure when computing 3" in {
      compute("3") match {
        case Right(n) => fail
        case Left(e) => e mustBe "Webservice1 returned no valid value"
      }
    }
    "return a left with the error message that states Webservice2's failure when computing 5" in {
      compute("5") match {
        case Right(n) => fail
        case Left(e) => e mustBe "Got this error after calling Webservice2: 'No value'"
      }
    }
    "return Int.MaxValue + 1987 when computing 10" in {
      compute("10") match {
        case Right(n) => Int.MaxValue + 1987L
        case Left(e) => fail()
      }
    }
  }
}
