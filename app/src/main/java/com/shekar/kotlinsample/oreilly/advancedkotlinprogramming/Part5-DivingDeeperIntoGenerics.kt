package com.shekar.kotlinsample.oreilly.advancedkotlinprogramming


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

fun operate(contravarianceExample: ContravarianceExample<Person>) {

}
/*====Type Projections in Kotlin & Summary====*/


fun main() {

    /*====Generic Constraints in Kotlin ====*/
    val db = Database<EmployeeEntityOne>()
    db.save(EmployeeEntityOne())

    val db2 = MultiEntityDb<EmployeeEntityTwo>()
    db2.save(EmployeeEntityTwo())

    //Function type restriction
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