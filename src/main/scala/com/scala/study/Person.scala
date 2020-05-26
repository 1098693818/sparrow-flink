package com.scala.study

class Person{
  private val id = Person.newPersonId()
  private var name = ""
  def this(name:String){
    this()
    this.name = name
  }
  def info() {printf("The id of %s is %d.\n",name,id)}
}

object Person {
  private var lastId = 0
  def newPersonId() = {
    lastId += 1
    lastId
  }

  def main(args: Array[String]): Unit = {
    val person1 = new Person("ziyu")
    val person2 = new Person("mingxing")
    person1.info()
    person2.info()
  }
}
