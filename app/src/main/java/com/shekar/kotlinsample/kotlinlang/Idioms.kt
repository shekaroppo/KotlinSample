package com.shekar.kotlinsample.kotlinlang

data class Sum( val x: Int){

   operator fun plus( sum : Sum): Int{
        val s= sum.x * x
        return s
    }
}

fun main() {
    val value = Sum(2) + Sum(2)
    println(value)
}