package com.shekar.kotlinsample.oreilly.advancedkotlinprogramming


import kotlin.properties.Delegates
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

/*====Generic Constraints in Kotlin ====*/
open class Department(val departmentName: String = "CSE")

interface Subject

class EmployeeEntityOne : Department()
class EmployeeEntityTwo : Department(), Subject

//Single Entity restriction
class Database<T : Department> {
    fun save(entity: T) {
        if (entity.departmentName == "CSE") {

        }
    }
}

//Mutliple  Entity restriction
class MultiEntityDb<T> where T : Department, T : Subject {
    fun save(entity: T) {
        if (entity.departmentName == "CSE") {

        }
    }
}

//Function type restriction
fun <T : Department> getDepartment(obj: T) {}

/*====Generics and Invariance====*/
open class Person

class Employee : Person()

fun operate(person: Person) {

}

fun operate(person: Array<Person>) {

}

fun operate(person: List<Person>) {

}

/*====Covariance in Kotlin====*/
interface CovarianceExample<out T> {
    fun getd(id: Int): T
    fun getAll(): List<T>
}

class CovarianceExampleImpl<T> : CovarianceExample<T> {
    override fun getAll(): List<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getd(id: Int): T {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}

fun operate(covarianceExampleImpl: CovarianceExample<Person>) {

}

/*====Contravariance in Kotlin====*/
interface ContravarianceExample<in T> {
    fun setObj(obj: T)
}

class ContravarianceExampleImpl<in T> : ContravarianceExample<T> {
    override fun setObj(obj: T) {
        TODO("not implemented")
    }
}
/*====Type Projections in Kotlin & Summary====*/

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

    /*====Generic Constraints in Kotlin ====*/
    val db = Database<EmployeeEntityOne>()
    db.save(EmployeeEntityOne())

    val db2 = MultiEntityDb<EmployeeEntityTwo>()
    db2.save(EmployeeEntityTwo())
    val department = getDepartment(EmployeeEntityOne())

    /*====Generics and Invariance====*/

    operate(Person())
    operate(Employee())

    operate(arrayOf(Person()))
    //operate(arrayOf(Employee())) // Not allowed because array of Employee is not sub type of array of People because conflict arise we pass array of employee and operate method add Person object to it as array is mutable object. This is called invariance.

    operate(listOf(Person())) // This solve above invariance issue as List are immutable and follow convenience principal
    operate(listOf(Employee()))

    /*====Covariance in Kotlin====*/
    operate(CovarianceExampleImpl<Person>())
    operate(CovarianceExampleImpl<Employee>())

    /*====Contravariance in Kotlin====*/
    operate(ContravarianceExampleImpl<Person>())
    //operate(ContravarianceExampleImpl<Employee>()) // Need to understand use of contravarience in kotlin

    /*====Type Projections in Kotlin & Summary====*/
    //Use-Type verience
    fun copy(list: MutableList<out Person>) {
    }


}


fun operate(contravarianceExample: ContravarianceExample<Person>) {

}




