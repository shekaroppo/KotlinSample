package com.shekar.kotlinsample.kotlinlang

import java.io.File

fun main(args: Array<String>) {

//    println("sum ${sum(1, 1)}")
//    println("sumInferred ${sumInferred(1, 1)}")
//    printSum(1, 1)
    val text = """
    >Tell me and I forget.
    >Teach me and I remember.
    >Involve me and I learn.
    >(Benjamin Franklin)
    """.trimMargin()
    println(text)
}

/*BasicSyntax*/

//Defining functions
fun sum(a: Int, b: Int): Int {
    val a =10
    return a + b
}

//Inferred return type // Single-expression functions
fun sumInferred(a: Int, b: Int) = a + b

//Unit return type which can be omitted
fun printSum(a: Int, b: Int): Unit {
    println("${a + b}")
}

//Defining variables
fun variableExample() {
    // immediate assignment
    val a: Int = 1
    // `Int` type is inferred
    val b = 1
    // Type required when no initializer is provided
    val c: Int
    c = 3       // deferred assignment
    //Variables that can be reassigned use the var keyword:
    var x = 5 // `Int` type is inferred
    x += 1
}

//Using string templates
fun stringTemplates() {
    var a = 1
    val s1 = "a is $a"
    a = 2
    val s2 = "${s1.replace("is", "was")}, but now is $a"
}

//Using conditional expressions
fun maxOf(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}

//if as expression
fun maxOfIf(a: Int, b: Int): Int = if (a > b) a else b

//Using nullable values and checking for null
fun parseInt(str: String): Int? {
    return null
}

//Using type checks and automatic casts
fun getString(obj: Any): Int? {
    if (obj is String) {
        // `obj` is automatically cast to `String` in this branch
        return obj.length
    }
    // `obj` is still of type `Any` outside of the type-checked branch
    return null
}

//Using for loop
fun loop() {
    //For loop
    val items = listOf("InnerClass", "B", "C")
    for (item in items) {
        println(item)
    }
    for (index in items) {
        println(index)
    }

    //While loop
    var i = 0
    while (i < items.size) {
        i++
    }

    //When
    when (items[0]) {
        "InnerClass" -> print("1")
    }
    when {
        "InnerClass" in items -> print("1")
        "B" in items -> print("2")
    }

    //lambda expressions
    items.filter { it.startsWith("InnerClass") }
        .sortedBy { it }
        .map { it.toUpperCase() }
        .forEach { println(it) }

    val map = mapOf("a" to 1, "b" to 2, "c" to 3)
    for ((k, v) in map) {

    }

}

/*Idioms*/

//Creating DTOs

data class Customer(val name: String, val email: String)

//Default values for function parameters
fun customerName(name: String = "Shekar") {}

//Lazy property
val customerId by lazy { }

//Extension function
fun String.alternateUpperCase() {

}

//Creating Singleton
object Resource {
    val name = "name"
}

//If not null shorthand
fun notNull() {
    val file = File("abc.text").list()
    println(file?.size)
}

//If not null and else shorthand
fun notNullElse() {
    val file = File("abc.text").list()
    println(file?.size ?: 0)
}

//Executing a statement if null
fun ifNullExecute() {
    val items = mapOf<String, Int>("InnerClass" to 1, "B" to 2, "C" to 3)
    println(items["D"] ?: 0)
}

//Get first item of a possibly empty collection
fun getFirstItemOfEmptyCollection() {
    val items = listOf<String>()
    println(items.firstOrNull() ?: "null list")
}

//Execute if not null
fun executeIfNotNull() {
    val name: String? = "Shekar"
    name?.let {
        println(name)
    }
}

//Execute if not null
fun executeIfNullorNotNull() {
    val name: String? = "Shekar"
    name?.let { println(name) } ?: println("Null")
}

//Single-expression functions

fun singleExpressionFunctions(a: Int, b: Int) = when (a + b) {
    5 -> "5"
    else -> "null"
}

//Calling multiple methods on an object instance (with)
fun multipleMethodsWith() {
    val name: String = "Shekar"
    with(name) {
        toUpperCase()
        println(length)
    }
}

//Consuming a nullable Boolean
fun consumingNull() {

    val b: Boolean? = null
    if (b == true) {
    } else {
        print("null")
    }
}

//Swapping two variables
fun swapping() {

    var a: String = "a"
    var b: String = "b"
    a = b.also { b = a }

}

//Coding Conventions
enum class Test(val value: Int) {
    A(0) {
        override fun toString(): String {
            return "InnerClass ENUM"
        }

        override fun sum() {
        }
    },
    B(1) {
        override fun sum() {
        }
    };

    abstract fun sum()
}

object Global {
    val PI = 3.14
}

open class Person : Any(){

}

class Employee : Person(){

}