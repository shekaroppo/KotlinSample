package com.shekar.kotlinsample.tuts

import android.app.Notification
import android.content.Context
import java.io.Closeable

/**
 * Created by Shekar on 2/22/17.
 */

object Global {
  val PI = 3.14
}

fun main(args: Array<String>): Unit {

  //Variable
  var name: String = "Shekar"
  var myname = "Shekar"
  myname = "Chandrashekar"
  val constantVariable = 10
  //Can't change val constantVariable=20
  //Can't assign null to var until explicitly declared with ? myname=null
  var age: Int?
  age = null
  println("age=" + age)

  //Function
  println("Hello," + start())
  println("I am ," + addTwoNumbers(20, 8) + " years old")
  println("I am ," + addTwoNumbers(20) + " years old")
  println("Add numbers " + addMultipleNumbers(1, 2, 3, 4))
  helloWorld(Message = "Hi", To = "Sheakr")

  //Extension Function Expression
  "This is me".hello()
  "This is Shekar".welcome()
  "This is Shekar".welcome("Shekar")
  "This".shouldEqual("This")
  //Infix annotation
  "This" shouldEqual "This"

  //ArrayList
  var numbers = arrayListOf(1, 2, 3, 4, 5, 6)
  println("Even numbers," + numbers.filter { it % 2 == 0 })

  var countriesCites = listOf(Pair("India", "Delhi"), "USA" to "WashingtonDC")
  for ((country, city) in countriesCites) {
    println("Countery : $country - City : $city")
  }

  /*Class
   - No new keyword
   - By default classes are final can't be inherited
   - use open keyword to make class inheritable
  */

  val mango = Fruit("Mango", 10)
  println(mango)
  var customer = Customer("shekarrex@gmail.com")
  customer.printCustomerDetails();
  var employee = Employee(2, "Chandra")
  employee.printEmployeeDetails()

  val human1 = Human("Shekar", 28)
  val human2 = Human("Shekar", 28)
  val human3 = human1
  println(human1)
  println(human1.hashCode())
  //Because Human is of type data(dto:data transfer object) "equals" comparison is done on data
  println(human1.equals(human2))
  println(human1.equals(human3))

  val customerKotlin = CustomerKotlin(1, "Shekar")
  val customerSecondKotlin = customerKotlin.copy(2)
  println(customerSecondKotlin)

  val persons = listOf(Human("Alice"),
      Human("Bob", age = 29))
  val oldest = persons.maxBy { it.age ?: 0 }
  println("The oldest is: $oldest")

  println("this is my text".convertSpacesToUnderscore())
  testNullabilty()

  //Function Expression

  //Higher Order Function
  val listOfNames = listOf("Shekar", "Vijay", "Ajay", "Rajesh")
  val shekar = listOfNames.filter { it == "Shekar" }
  println("Higher Order Function:" + shekar)


  //Expression
  println(ifAsExpression(10))


  /* Casting :
  - By default Kotlin classes are final
  - If you want to inherit from class make it open
  - if you want to user super class variable make it open and add ovveride anitation in subclass
  - animal: Animal? will allow passing null to checkNumberOfKills
  - animal: Animal means animal can't be null
  - animal?.kills is equal to if(animal != null)
  - animal!!.kills is equal to if(animal ==null || animal !=null)

  */
  println(checkNumberOfKills(Lion()))
  println(checkNumberOfKills(Tiger()))
  println(checkNumberOfKills(null))

  //Functions
  higherOrderFun { x, y -> x + y }

  //macro
  using(obj = Closeable { }) {
    //DO some action here and it will be closed
  }
}


fun ifAsExpression(input: Int): Any {
  val result = if (input > 100) {
    println("Something")
    30
  } else {
    "Return type becomes Any"
  }
  return result
}

fun helloWorld(To: String, Message: String) {
  println(To + ": " + Message)
}

fun String.hello(){
  println("Hello!! it's me ")
}
fun String.welcome(){
  println("Welcome!! $this")
}
fun String.welcome(name:String){
  println("Welcome!! $name")
}
infix fun  String.shouldEqual(string: String) {
  string == this
}

inline fun notification(context: Context, func: Notification.Builder.() -> Unit): Notification {
  val builder = Notification.Builder(context)
  builder.func()
  return builder.build()
}

fun testNullabilty() {
  val s1: String = "Always ot null"
  val s2: String? = null
  val i: Int = s1.length
  val j: Int? = s2?.length ?: 0
  println(i)
  println(j)
}

private fun String.convertSpacesToUnderscore(): String {
  return this.replace(" ", "_")
}

fun start(): String = "World!"
fun addTwoNumbers(a: Int, b: Int = 0): Int {
  return a + b
}

fun addMultipleNumbers(vararg number: Int): Int {
  return number.sum()
}

class Fruit(val name: String, val qty: Int? = null)

class Customer(email: String = "shekar@gmail.com") {
  val id: Int = 1
  var name: String = "Shekar"
  val email = email
  fun printCustomerDetails() {
    println("Customer Details:- Id: $id Name: $name email: $email")
  }

  init {
    println("Init get called")
  }
}

class Employee(val id: Int, val name: String) {
  fun printEmployeeDetails() {
    println("Employee Details:- Id: $id Name: $name")
  }
}

data class Human(val name: String, val age: Int? = null)

open class Animal {
  open val kills: Int = 0
}

class Tiger : Animal() {
  override var kills = 10
}

class Lion : Animal() {
  override var kills = 20
}

fun checkNumberOfKills(animal: Animal?): Int? {
  return animal?.kills
}

fun higherOrderFun(func: (Int, Int) -> Int) {
  println(func(2, 3))
}

fun mySum(x: Int, y: Int): Int {
  return x + y
}

fun using(obj: Closeable, action: () -> Unit) {
  try {
    action()
  } finally {
    obj.close()
  }
}



