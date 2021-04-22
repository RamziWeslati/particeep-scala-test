package com.particeep.test.basic

import play.api.i18n.Lang

import org.scalatestplus.play.PlaySpec
import Refactoring._

class RefactoringTest extends PlaySpec {

  "Refactoring" should {
  	"get categories of a list of files" in {
  		val files = List(
  			File("name1", "category1"),
  			File("name2", "category2"),
  			File("name3", "category3"))
  		getCategories(files) mustBe Set("category1", "category2", "category3")
  	}
  	"get categories of a null" in {
  		getCategories(null) mustBe Set[String]()
  	}
  	"get categories of a list of files containing nulls" in {
  		 val files = List(
  			File("name1", "category1"),
  			File("name2", null),
  			File("name3", "category3"))
  		getCategories(files) mustBe Set("category3", "category1")
  	}
  }
}
