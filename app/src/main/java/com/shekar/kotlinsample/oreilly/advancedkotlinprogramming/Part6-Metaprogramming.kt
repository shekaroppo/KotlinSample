package com.shekar.kotlinsample.oreilly.advancedkotlinprogramming

import java.lang.reflect.Type
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

/*====Using Java Reflection with Kotlin====*/
class Transaction(val id: Int, val amount: Double, var desc: String) {
    fun validate() {

    }
}

fun introspection(obj: Any) {
    println("Class ${obj.javaClass.simpleName}")
    obj.javaClass.declaredFields.forEach {
        println("${it.name} of ${it.type}")
    }
    println("\nFunction\n")
    obj.javaClass.declaredMethods.forEach {
        println("${it.name}")
    }

}

fun getType(obj: Type) {
    println(obj.typeName)
}


/*====Using Kotlin Reflection====*/
fun getType(obj: KClass<*>) {
    println(obj.qualifiedName)
}

/*====Type Erasure on the JVM====*/
fun <T> printList(list: List<T>) {
    if (list is List<*>) {
        println("this is list")
    }
}

/*====Reified Generics in Kotlin====*/
inline fun <reified T> erased(list: List<Any>) {
    if (list is T) {
        println("this is list")
    }
}

/*====Custom Annotations in Kotlin====*/
@Target(AnnotationTarget.CLASS)
@MustBeDocumented
@Retention
@Repeatable
annotation class Table(val name: String)

@Target(AnnotationTarget.FIELD)
annotation class Field(val name: String)

@Table(name = "ContactTable")
data class Contact(val id: Int, @Field(name = "NAME") val name: String, val email: String)

fun main() {
    /*====Using Java Reflection with Kotlin====*/
    introspection(Transaction(1, 100.00, "Sal"))
    getType(Transaction::class.java)

    /*====Using Kotlin Reflection====*/
    val classInfo = Transaction::class
    println(classInfo) //Kclass
    classInfo.memberProperties.forEach {
        println("${it.name} of ${it.returnType}")
    }
    classInfo.constructors.forEach {
        println("${it.name} - ${it.parameters}")
    }
    getType(Transaction::class)

    //Creating new instance
    val constructor = ::Transaction
    println(constructor)
    val transactionCall = constructor.call(1, 200, "Sal2") //
    println(transactionCall.desc)
    val validateFunc = transactionCall.validate()
    //or
    val transactionCallBy = constructor.callBy(
        mapOf(
            constructor.parameters[0] to 1,
            constructor.parameters[1] to 200,
            constructor.parameters[2] to "Sal2"
        )
    )
    val idParam = constructor.parameters.first { it.name == "id" }
    val amountParam = constructor.parameters.first { it.name == "amount" }
    val descParam = constructor.parameters.first { it.name == "desc" }
    val transactionCallBy2 = constructor.callBy(
        mapOf(
            idParam to 1,
            amountParam to 200,
            descParam to "Sal2"
        )
    )
    //Read value of property at runtime
    val trans = Transaction(1, 2000.0, "de")
    val nameProperty = Transaction::class.memberProperties.find { it.name == "desc" }
    println(nameProperty?.get(trans))

    /*====Type Erasure on the JVM====*/
    val listStrings = listOf("One", "Two", "Three")
    val listNumber = listOf(1, 2, 3)
    printList(listStrings)
    printList(listNumber)

    /*====Reified Generics in Kotlin====*/
    erased<List<String>>(listStrings)
    erased<List<Int>>(listNumber)


    /*====Custom Annotations in Kotlin====*/
    val anno = Contact::class.annotations.find { it.annotationClass.simpleName == "Table" }
    println(anno)
}