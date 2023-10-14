package com.example.appbar

class ModelClass {


    var name:String?=null
    private var number:String?=null

    fun setNames(name:String) {
        this.name = name
    }
    fun setNumber(number: String) {
        this.number = number
    }
    fun  getNames():String {
        return name.toString()
    }

    fun getNumber():String{
        return number.toString()

    }
}