package com.rajibul.sharedpreferences

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class ListAdapter(): BaseAdapter() {
    var colorlist:Int = 0
    var color1: String = ""
    var color2: String = ""
    
    fun updateValues(colorList: Int, color1: String, color2: String){
        this.colorlist = colorList
        this.color1 = color1
        this.color2 = color2
        notifyDataSetChanged()
    }
    override fun getCount(): Int {
        return colorlist.toInt()
    }

    override fun getItem(p0: Int): Any {
        return 1
    }

    override fun getItemId(p0: Int): Long {
        return 1L
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View? {
        var view = LayoutInflater.from(p2?.context).inflate(R.layout.recycler_list, p2, false)
        var screen = view.findViewById<TextView>(R.id.tvList)
        System.out.println("color1 $color1 color2 $color2")

        if (p0 % 2 == 0) {
            screen.setBackgroundColor(Color.parseColor(color1))
            screen.setText(color1)
        } else {
            screen.setBackgroundColor(Color.parseColor(color2))
            screen.setText(color2)

        }

        return view
    }
}