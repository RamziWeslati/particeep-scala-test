package com.particeep.test.basic

/**
 * What is the complexity of the function ?
 *
 * The complexity of the function getCategories is O(n^2) 
 *
 * The complexity of the refactored function is O(n)
 */
object Refactoring {

  case class File(
    name:     String,
    category: String
  )

  def getCategories(files: List[File]): Set[String] = {
    if(files != null) {
      files.map(_.category).toSet.filter(_ != null)
    } else {
      Set[String]()
    }
  }
}
