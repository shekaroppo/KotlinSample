package com.shekar.kotlinsample.tuts

import kotlin.system.measureTimeMillis

/**
 * Created by Shekar on 2/27/17.
 */

//1. Explosive Placeholders
fun test(day: String): String {
  if (day.equals("Monday")) {
    return day
  } else {
    TODO("doSomething")
  }
}

//2. Semantic validation
fun join(sep: String, strings: List<String>): String {
  require(sep.length < 2) { "sep.length <2: " + sep }
//  if(sep.length<2){
//    throw IllegalArgumentException("sep.length <2: " +sep)
//  }
  return "success"
}

//3.Anything or Nothing
//4.Let
fun test() {
  var user: User? = null
  user?.let {

  }
  user?.apply {

  }
  user?.run {

  }

}

class User

//5. Multiline string literals
var ring = "foo\nbar\nbaz"
val string = """ foo bar baz""".trimIndent()
val stringVal = """|foo|bar|baz""".trimMargin()

//6.Lazy but speedy

class NamePrinter(val firstName: String, val secondName: String) {
  val fullName: String by lazy { "$firstName $secondName" }
}

//7. Code block meassurment
val helloTook = measureTimeMillis {
  println("Hello")
}

//8. Deprecation level

@Deprecated("Use default join")
fun join(string1: String, string2: String) {
}

//9. Deprecation Replacement
@Deprecated("Use strings.joinToString(sep)", replaceWith = ReplaceWith("strings.joinToString(sep)"))
fun combine(sep: String, strings: List<String>) {
}

fun main(args: Array<String>) {
  join("2", "3")
  combine("2", listOf("1", "3"))
}

//10. Erasing Erasure
@JvmName("sortString")
fun sort(strings: List<String>) {

}
@JvmName("sortInts")
fun sorta(ints: List<String>) {

}

