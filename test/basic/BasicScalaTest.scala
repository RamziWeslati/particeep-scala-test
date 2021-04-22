package com.particeep.test.basic

import play.api.i18n.Lang

import org.scalatestplus.play.PlaySpec
import BasicScala._

class BasicScalaTest extends PlaySpec {

  "BasicScala" should {
    "encode parameters in url" in {
      encodeParamsInUrl(
      	Map("sort_by" -> "name",
       		"order_by" -> "asc",
       		"user_id" -> "12")) mustBe "?sort_by=name&order_by=asc&user_id=12"
      encodeParamsInUrl(Map[String, String]()) mustBe ""
    }
    "compute email" in {
      isEmail("jean@particeep.com") mustBe true
      isEmail("jean_particeep.com") mustBe false
      isEmail("jean@particeepcom") mustBe false
      isEmail("jean adds@particeep.com") mustBe false
    }
    "compute the power of n" in {
      power(2, 3) mustBe 8
      power(2, -3) mustBe 1/8.0
      power(2, 0) mustBe 1
    }
  }
}
