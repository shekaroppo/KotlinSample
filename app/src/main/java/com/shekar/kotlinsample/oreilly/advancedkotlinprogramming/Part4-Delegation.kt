package com.shekar.kotlinsample.oreilly.advancedkotlinprogramming

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/*====Delegating Member Functions in Kotlin====*/
interface Rectangle {
    fun area(width: Int, height: Int): Int
}

interface Square {
    fun area(length: Int): Int
}

class Window(val bound: Rectangle) {
    fun area(x: Int, y: Int) = bound.area(x, y)
}

class WindowWithDelegate(rectBound: Rectangle, squareBound: Square) : Rectangle by rectBound, Square by squareBound

/*====Delegating Properties in Kotlin====*/
class DelegatingPropertiesExample {
    var delegate: String by DelegateExample()
}

class DelegateExample : ReadWriteProperty<DelegatingPropertiesExample, String> {
    var value: String = ""
    override fun getValue(thisRef: DelegatingPropertiesExample, property: KProperty<*>): String {
        return "Hello! $value"
    }

    override fun setValue(thisRef: DelegatingPropertiesExample, property: KProperty<*>, value: String) {
        this.value = value
    }
}


fun main() {

    /*====Delegating Member Functions in Kotlin====*/

    val rectangle = object : Rectangle {
        override fun area(width: Int, height: Int): Int {
            return width * height
        }
    }

    val square = object : Square {
        override fun area(length: Int): Int {
            return length * length
        }
    }

    val window = Window(rectangle)
    println(window.area(5, 5))

    //With Delegate
    val windowWithDeligate = WindowWithDelegate(rectangle, square)
    println(windowWithDeligate.area(2, 2))
    println(windowWithDeligate.area(3))

    /*====Delegating Properties in Kotlin====*/
    val delegatingPropExample = DelegatingPropertiesExample()
    println(delegatingPropExample.delegate)
    delegatingPropExample.delegate = "Devika"
    println(delegatingPropExample.delegate)
    /*====Built-in Delegated Properties in Kotlin====*/
    /*====Local Delegates in Kotlin====*/
    /*====Extension Properties in Kotlin & Summary====*/
}


