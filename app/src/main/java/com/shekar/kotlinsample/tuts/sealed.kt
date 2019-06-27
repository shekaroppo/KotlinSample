package com.shekar.kotlinsample.tuts

/**
 * Created by Shekar on 2/27/17.
 */
sealed class PageResult {
    class Success(val url: String) : PageResult()
    class Error(val code: Int, val message: String) : PageResult()
}

fun getPage(): PageResult {
    val someOperationIsSuccsesfull = false
    if (someOperationIsSuccsesfull) {
        return PageResult.Success("http://url")
    }
    TODO("doSomething")
    return PageResult.Error(404, "Not Found")
}

fun main(args: Array<String>) {

}


fun greetFun(function: String.() -> String) {
    var add: Int.(a: Int) -> Int = { a -> this + a }
    println(1.add(1))
}
