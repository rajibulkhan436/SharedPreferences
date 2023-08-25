package com.rajibul.sharedpreferences

object Singleton {
    init{
        println("in singleton init")
    }
    val sharedPref : SharedPrefClass = SharedPrefClass()
}