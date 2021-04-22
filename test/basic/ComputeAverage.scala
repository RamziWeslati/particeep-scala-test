package com.particeep.test.basic

import play.api.i18n.Lang

import org.scalatestplus.play.PlaySpec
import ComputeAvarage._

class ComputeAverageTest extends PlaySpec {

  "ComputeAverage" should {
  	"compute the average of a list of doubles" in {
  	  average(List[Double](1, 10, 16)) mustBe Some(9)
  	  average(List[Double]()) mustBe None
  	}
  }
}
