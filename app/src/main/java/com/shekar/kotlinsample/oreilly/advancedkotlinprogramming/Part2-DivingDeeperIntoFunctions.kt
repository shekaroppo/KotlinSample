package com.shekar.kotlinsample.oreilly.advancedkotlinprogramming


fun main() {

    /*====Local Functions in Kotlin====*/
    foo("Hello")

    /*====Infix Functions in Kotlin====*/
    println("Hello".isEqual("Hello")) //Without infix
    println("Hello" isEqual "Hello")//With infix

    /*====Higher order functions examples which accept Anonymous Functions/Lambda Expressions*/
    /*====Functions with interface as parameter====*/
    add(1, 1, object : AdditionInterface {
        override fun execute(x: Int, y: Int): Int {
            return x + y
        }
    })

    /*====Named Function====*/
    fun addition(x: Int, y: Int): Int = x + y
    add(1, 1, ::addition)

    /*====Lambda Function====
        Syntax of lambda
        { parameter -> function body }
    */

    val lambda1: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
    add(1, 1, lambda1)
    //or
    val lambda2 = { x: Int, y: Int -> x + y }
    add(1, 1, lambda2)
    //or
    val lambda3: (Int, Int) -> Int = { x, y -> x + y }
    add(1, 1, lambda3)
    //or
    add(1, 1, { x, y -> x + y })
    //or
    add(1, 1) { x, y -> x + y }
    //or
    add(1, { it + it }) //For single param we can use it

    /*====Lambda Extensions or lambda with receivers in Kotlin====
    Int.() -> Long  // taking an integer as receiver producing a long
    String.(Long) -> String // taking a string as receiver and long as parameter producing a string
    GUI.() -> Unit // taking an GUI and producing nothing
    */
    var greet: String.() -> Unit = { println("Hello $this") }
    greetFun({ "Hi" })


    /*====Anonymous function====
        Syntax of Anonymous function
        fun(parameter):output { return function body}
    */
    val anonymousFunction = fun(x: Int, y: Int): Int { return x + y }
    add(1, 2, anonymousFunction)
    //or
    add(1, 2, fun(x, y): Int { return x + y })
    //or
    add(1, fun(x): Int { return x + x })

    /*====Inline function====*/
    operation { println("op") }
    inlineFunction { println("inline op") }
    inlineFunction {
        println("inline op")
        return
    }
    crossinlineFunction {
        println("inline op")
        // return not allowed
    }
    noinlineFunction({ println("inline op") }, { println("noinline op") })
    inlineWithFunctionReference { println("inlineWithFunctionReference") }
    inlineFunctionWithException { println("inlineFunctionWithException") }

    /*====Returns and Local Returns in Kotlin====*/
    returnFunction()
    localReturnFunction()
    returnOnCustomForEachFunction()
    localReturnFunctionWithLabel()
    returnWithAnonymousFunction()

    /*====Tail Recursion in Kotlin====*/
    println(recursiveSum(3))
    println(recursiveTailrecSum(3))

    /*====Operator Overloading in Kotlin====*/
    println(Sum(1).plus(Sum(1)))
    println(Sum(1) + (Sum(1)))

}


//Local Functions in Kotlin
fun foo(fooParam: String) {
    fun bar(barParam: String) {
        println("$barParam This is inner function")
        println("$fooParam this is outer function")
    }
    bar(fooParam) //bar is not accessible outside foo
}

/*====Infix Functions in Kotlin====*/
infix fun String.isEqual(param: String) = this == param

/*====Functions with interface as parameter====*/
interface AdditionInterface {
    fun execute(x: Int, y: Int): Int
}

fun add(x: Int, y: Int, action: AdditionInterface) {
    println(action.execute(x, y))
}

/*====Higher order Function accepting lambda function====*/
fun add(x: Int, y: Int, action: (Int, Int) -> Int) {
    println(action(x, y))
}

fun add(x: Int, action: (Int) -> Int) = println(action(x))

/*====Lambda Extensions or lambda with receivers in Kotlin====*/
fun greetFun(function: String.() -> String) {
}

/*====Inline Function====*/
fun operation(op: () -> Unit) {
    println("before calling op")
    op()
    println("after calling op ")
}

inline fun inlineFunction(op1: () -> Unit) {
    println("before calling op")
    op1()
    println("after calling op ")
}

inline fun crossinlineFunction(crossinline op1: () -> Unit) {
    println("before calling op")
    op1()
    println("after calling op ")
}

inline fun noinlineFunction(op1: () -> Unit, noinline op2: () -> Unit) {
    println("before calling op")
    op1()
    op2()
    println("after calling op ")
}

inline fun inlineWithFunctionReference(op: () -> Unit) {  //This inline is not allowed as op() is assigned to reference, as inlining the code is not possible in this case
    val reference = op()
}

inline fun inlineFunctionWithException(op: () -> Unit) {
    op()
    throw Exception("A message") //IDE give option if we want to go function body or call site.
}


/*====Returns and Local Returns in Kotlin====*/
fun returnFunction() {
    val number = 1..100
    number.forEach {
        if (it % 5 == 0) {
            return        // This will exit the returnFunction hence doesn't print hello, this is because forEach is inline function
        }
    }
    println("Hello")
}

fun returnOnCustomForEachFunction() {
    val number = 1..100
    number.myForEach {
        if (it % 5 == 0) {
            // return   // This is not allowed because non local returns are allowed only on Inline functions.
        }
    }
    println("Hello")
}

fun <T> Iterable<T>.myForEach(action: (T) -> Unit): Unit {
    for (element in this) action(element)
}

fun localReturnFunction() {
    val number = 1..100
    number.forEach {
        if (it % 5 == 0) {
            return@forEach  // This will exit for loop hence print hello
        }
    }
    println("Hello")
}

fun localReturnFunctionWithLabel() {
    val number = 1..100
    number.forEach myLabal@{
        if (it % 5 == 0) {
            return@myLabal  // Same as localReturnFunction but we created our own label @myLabal instead of default label @forEach
        }
    }
    println("Hello")
}

fun returnWithAnonymousFunction() {
    val number = 1..10
    number.forEach {
        innerFunction(it)
    }
    println("Hello")
}

val innerFunction = fun(element: Int) {
    if (element % 5 == 0) {
        return        // This will exit the forEach loop and print hello because here return will exit the nearest fun which is anonymous function
    }
    println(element)
}

/*====Tail Recursion in Kotlin====*/
fun recursiveSum(i: Int): Int {
    return if (i <= 0) {
        i
    } else {
        i + recursiveSum((i - 1))
    }
}

tailrec fun recursiveTailrecSum(i: Int, semiResult: Long = 0): Long {
    return if (i <= 0) {
        semiResult
    } else {
        recursiveTailrecSum((i - 1), i + semiResult)
    }
}

/*====Operator Overloading in Kotlin====*/
class Sum(private val x: Int) {
    operator fun plus(sum: Sum): Int {  //operator keyword allow overloading + to perform our own intended action inside plus function
        return sum.x + x
    }
}

