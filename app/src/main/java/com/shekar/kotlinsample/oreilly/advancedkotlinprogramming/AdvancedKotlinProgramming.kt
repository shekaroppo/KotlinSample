//package com.shekar.kotlinsample.oreilly.AdvancedKotlinProgramming
//
//
//fun main() {
//
//    routeHandler("/index.html") {
//        if (request.query != "") {
//            //process
//        }
//        //Lambda Extensions in Kotlin
//        response {
//            status {
//                code = 404
//                desc = "Not found"
//            }
//        }
//        //Invoking instances in Kotlin
//        invokeResponse {
//            code = 404
//            desc = "Not found"
//        }
//    }
//
//    //Functional Constructs
//
//
//}
//
////Lambda Extensions in Kotlin : It allow us to create very fluent expressive DSL
//class Request(val method: String, val query: String, val contentType: String)
//
//class Response(var content: String, var status: Status) {
//    fun status(status: Status.() -> Unit) {
//    }
//}
//
//class InvokeResponse(var content: String, var status: Status) {
//    operator fun invoke(function: Status.() -> Unit) {
//    }
//}
//
//class Status(var code: Int, var desc: String)
//
//class RouteHandler(val request: Request, val response: Response, val invokeResponse: InvokeResponse) {
//    var executedNext = false
//    fun next() {
//        executedNext = true
//    }
//}
//
//fun routeHandler(path: String, f: RouteHandler.() -> Unit): RouteHandler.() -> Unit = f
//fun response(response: Response.() -> Unit) {}
//
//class InnerClassInnerKeyword {
//    fun printClassName() {
//        println("With inner keyword , inner class can use outer class variable $name")
//    }
//}