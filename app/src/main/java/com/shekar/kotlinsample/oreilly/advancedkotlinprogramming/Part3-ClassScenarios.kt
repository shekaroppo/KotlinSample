package com.shekar.kotlinsample.oreilly.advancedkotlinprogramming

fun main() {
    //Fields in Kotlin
    val example = FieldsInKotlin()
    example.printVal(10)

    //Late Initialization in Kotlin
    val lateInitialization = LateInitialization()
    lateInitialization.name = "Shekar"
    println(lateInitialization.getMyName())

    //Nested Classes in Kotlin
    val innerClass = OuterClass.InnerClass()
    innerClass.printClassName()
    val innerClassWithInnerKeyword = OuterClass("Shekar").InnerClassInnerKeyword()
    innerClassWithInnerKeyword.printClassName()

    //Companion Objects in Kotlin
    val log = MyLog.Factory.createLogFile("myfile.txt")
    val logCompanion = MyLog.createLogFile("myfile.txt")

    //Hiding Constructors in Kotlin
    val myLog = HidingConstructors.createLogFile("myfile.txt") //Not allowed as MyLog constructor is private

    //Sealed Classes in Kotlin
    when (getResult(true)) {
        is Success -> println("Success")
        is Error -> println("Error")
    }

    //Type Aliases
    val emp = Emp("Shekar")
    emp.printName()
}

//Fields in Kotlin
class FieldsInKotlin {
    var devika: String = ""
    var amount: Int = 0
        get() = if (field > 0) 5 else 10
        set(value) {
            if (value > 100)
                field = value
        }

    fun printVal(i: Int) {
        amount = i
        println(amount)
    }
}

//Late Initialization in Kotlin
class LateInitialization {
    lateinit var name: String

    fun getMyName(): String {
        return name
    }
}

//Nested Classes in Kotlin
class OuterClass(val name: String) {
    class InnerClass {
        fun printClassName() {
            println("InnerClass cant use outer class variable i.e name")
        }
    }

    inner class InnerClassInnerKeyword {
        fun printClassName() {
            println("With inner keyword , inner class can use outer class variable $name")
        }
    }
}

//Companion Objects in Kotlin
class MyLog private constructor(filename: String) {
    object Factory {
        fun createLogFile(filename: String) = MyLog(filename)
    }

    companion object FactoryCompanion {
        @JvmStatic
        fun createLogFile(filename: String) = MyLog(filename)
    }
}

//Hiding Constructors in Kotlin
class HidingConstructors private constructor(filename: String) {
    companion object FactoryCompanion {
        @JvmStatic
        fun createLogFile(filename: String) =
            HidingConstructors(filename)
    }
}

//Sealed Classes in Kotlin
fun getResult(value: Boolean): PageResult {
    return if (value) Success() else Error()
}

sealed class PageResult  //PageResult can't be extended outside this class
class Success : PageResult()
class Error : PageResult()

//Type Aliases
typealias  Name = String

data class Emp(val name: Name) {
    fun printName() {
        println(name)
    }
}
