package com.rajibul.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class SharedPrefClass {
    var sharedPrefs : SharedPreferences?=null
    var editor : SharedPreferences.Editor?=null

    fun initPrefs(context: Context){
        if(sharedPrefs == null){
            sharedPrefs = context.getSharedPreferences(
                context.resources.getString(R.string.app_name),
                Context.MODE_PRIVATE)
            editor = sharedPrefs?.edit()
        }
    }

    fun saveString(key: String, value:String){
        editor?.putString(key, value)
        editor?.commit()
        editor?.apply()
    }
    fun saveInt(key: String, value: Int){
        editor?.putInt(key, value)
        editor?.commit()
        editor?.apply()
    }

    fun getString(name: String) : String{
        return sharedPrefs?.getString(name , "#fffff")?:"#fffff"
    }
    fun getInt(size: String):Int{
        return sharedPrefs?.getInt(size, 0)?:0
    }

    fun clearPrefs(){
        editor?.clear()
        editor?.commit()
    }
}
