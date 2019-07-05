package com.shekar.kotlinsample.oreilly.advancedkotlinprogramming

import kotlinx.coroutines.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

//[Kotlin Coroutines Tutorial (STABLE VERSION) - async / await, withContext, launch](https://www.youtube.com/watch?v=jYuK1qzFrJg)
//https://play.kotlinlang.org/hands-on/Introduction%20to%20Coroutines%20and%20Channels/02_BlockingRequest

fun printlnThreadDelay(msg: String) {
    Thread.sleep(1000)
    println(msg)
}

suspend fun printlnCoroutinesDelay(msg: String) {
    // delay need to be called from suspend function
    // suspend will not block the thread like Thread.sleep
    delay(1000)
    println(msg)
}

suspend fun fetchData(v: Int): Int {
    // delay need to be called from suspend function
    // suspend will not block the thread like Thread.sleep
    println("Fetching data from server ...")
    delay(1000)
    return v * 10
}

private fun exampleThread() {
    println("One")
    printlnThreadDelay("Two")
    println("Three")
}

fun exampleBlocking1() {
    println("One")
    runBlocking {
        printlnCoroutinesDelay("Two")
    }
    println("Three")
}

fun exampleBlocking2() = runBlocking {
    println("One-from thread ${Thread.currentThread().name}")
    printlnCoroutinesDelay("Two-from thread ${Thread.currentThread().name}")
    println("Three-from thread ${Thread.currentThread().name}")
}

fun exampleBlockingDispatchersDefault() {
    //Dispatchers.Default start new thread
    println("One-from thread ${Thread.currentThread().name}")
    runBlocking(Dispatchers.Default) {
        printlnCoroutinesDelay("Two-from thread ${Thread.currentThread().name}")
    }
    println("Three-from thread ${Thread.currentThread().name}")
}

fun exampleBlockingGlobalScopeLaunch() = runBlocking {
    //GlobalScope.launch doesn't block main thread
    println("One-from thread ${Thread.currentThread().name}")

    GlobalScope.launch {
        printlnCoroutinesDelay("Two-from thread ${Thread.currentThread().name}")
    }

    println("Three-from thread ${Thread.currentThread().name}")
    delay(3000) // To stop program exit before GlobalScope.launch return
}

fun exampleBlockingGlobalScopeLaunchWithJob() = runBlocking {
    //Use join to eliminate delay as previous example
    println("One-from thread ${Thread.currentThread().name}")

    val job = GlobalScope.launch {
        printlnCoroutinesDelay("Two-from thread ${Thread.currentThread().name}")
    }

    println("Three-from thread ${Thread.currentThread().name}")
    job.join()
}

fun exampleBlockingLaunchOnMainThread() = runBlocking {
    //Use launch to run coroutine on same scope and system will wait fro execution but on same thread
    println("One-from thread ${Thread.currentThread().name}")

    launch {
        printlnCoroutinesDelay("Two-from thread ${Thread.currentThread().name}")
    }

    println("Three-from thread ${Thread.currentThread().name}")
}

fun exampleBlockingLaunchOnDiffThread() = runBlocking {
    //Use launch to run coroutine on same scope and system will wait fro execution but on same thread
    println("One-from thread ${Thread.currentThread().name}")

    launch(Dispatchers.Default) {
        printlnCoroutinesDelay("Two-from thread ${Thread.currentThread().name}")
    }

    println("Three-from thread ${Thread.currentThread().name}")
}

fun exampleBlockingCustomerDispatcher() = runBlocking {
    //Use launch to run coroutine on same scope and system will wait fro execution but on same thread
    println("One-from thread ${Thread.currentThread().name}")

    val customDispatcher = Executors.newFixedThreadPool(2).asCoroutineDispatcher()
    launch(customDispatcher) {
        printlnCoroutinesDelay("Two-from thread ${Thread.currentThread().name}")
    }

    println("Three-from thread ${Thread.currentThread().name}")
    (customDispatcher.executor as ExecutorService).shutdown()// customDispatcher don't shutdown by default
}

//launch return job and not value , following example show how to return value from call.

fun exampleBlockingAsyncAwait() = runBlocking {
    val startTime = System.currentTimeMillis()
    val value1 = async { fetchData(1) }.await() //await block the thread until finished thus calls are made sequentially
    val value2 = async { fetchData(1) }.await()
    val value3 = async { fetchData(1) }.await()
    val sum = value1 + value2 + value3
    val endTime = System.currentTimeMillis()
    println("Sum is ${sum} and Time taken to execute ${endTime - startTime}")
}

fun exampleBlockingAsyncAwaitConcurent() = runBlocking {
    val startTime = System.currentTimeMillis()
    val deferred1 = async { fetchData(1) }
    val deferred2 = async { fetchData(1) }
    val deferred3 = async { fetchData(1) }
    val sum = deferred1.await() + deferred2.await() + deferred3.await()
    val endTime = System.currentTimeMillis()
    println("Sum is ${sum} and Time taken to execute ${endTime - startTime}")
}

//If concurrency is not needed then use async/await or withContext

fun exampleBlockingWithContext() = runBlocking {
    //withContext is same as async/await
    val startTime = System.currentTimeMillis()
    val value1 = withContext(Dispatchers.Default) { fetchData(1) }
    val value2 = withContext(Dispatchers.Default) { fetchData(1) }
    val value3 = withContext(Dispatchers.Default) { fetchData(1) }
    val sum = value1 + value2 + value3
    val endTime = System.currentTimeMillis()
    println("Sum is ${sum} and Time taken to execute ${endTime - startTime}")
}

fun main() {
//    //Classical thread
//    exampleThread()
//
//    //With coroutines
//    exampleBlocking1()
//    exampleBlocking2()
//    exampleBlockingDispatchersDefault()
//    exampleBlockingGlobalScopeLaunch()
//    exampleBlockingGlobalScopeLaunchWithJob()
//    exampleBlockingLaunchOnMainThread()
//    exampleBlockingLaunchOnDiffThread()
//    exampleBlockingCustomerDispatcher()
//    exampleBlockingAsyncAwait()
//    exampleBlockingAsyncAwaitConcurent()
    exampleBlockingWithContext()


}