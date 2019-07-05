package com.shekar.kotlinsample.oreilly.advancedkotlinprogramming


import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/*====Delegating Member Functions in Kotlin====*/
interface Rectangle {
    fun area(width: Int, height: Int): Int
}

val rectangle = object : Rectangle {
    override fun area(width: Int, height: Int): Int {
        return width * height
    }
}

interface Square {
    fun area(length: Int): Int
}

val square = object : Square {
    override fun area(length: Int): Int {
        return length * length
    }
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

/*====Built-in Delegated Properties in Kotlin====*/
/*----Delegates.observable----*/
var builtInObservableDelegate: String by Delegates.observable(
    "Shekar", { property, oldValue, newValue ->
        println("property = ${property.name} old value = $oldValue new Value = $newValue")
    }
)
/*----Delegates.vetoable----*/
var builtInVetoDelegate: String by Delegates.vetoable("Shekar", { prop, old, new -> new.startsWith("S") })


/*====Local Delegates in Kotlin====*/
val lazy by lazy { initLazy() }

fun initLazy() {
    println("hello lazy")
}

/*====Extension Properties in Kotlin & Summary====*/
val String.hasDollor: Boolean
    get() = this.contains("$")


fun main() {

    /*====Delegating Member Functions in Kotlin====*/
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
    /*====Delegates.observable====*/
    builtInObservableDelegate = "Devika"
    println(builtInObservableDelegate)

    /*====Delegates.vetoable====*/
    builtInVetoDelegate = "Devika"
    println(builtInVetoDelegate)
    builtInVetoDelegate = "Sachin"
    println(builtInVetoDelegate)

    /*====Local Delegates in Kotlin====*/
    lazy

    /*====Extension Properties in Kotlin & Summary====*/
    println("Hello".hasDollor)
    println("Hello $".hasDollor)
}






