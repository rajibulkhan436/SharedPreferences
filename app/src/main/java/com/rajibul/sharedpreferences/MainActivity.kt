package com.rajibul.sharedpreferences

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.rajibul.sharedpreferences.AppConstants.Companion.color1
import com.rajibul.sharedpreferences.AppConstants.Companion.color2
import com.rajibul.sharedpreferences.AppConstants.Companion.listSize
import com.rajibul.sharedpreferences.databinding.ActivityMainBinding
import com.rajibul.sharedpreferences.databinding.DialogBoxBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    lateinit var listAdapter:ListAdapter
    lateinit var dialog : Dialog
    lateinit var dialogBinding:DialogBoxBinding
    var color1 = ""
    var color2 = ""
    var listSize = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.activity = this
        listAdapter = ListAdapter()
        Singleton.sharedPref.initPrefs(this)

        listSize = Singleton.sharedPref.getInt(AppConstants.listSize)
        color1 = Singleton.sharedPref.getString(AppConstants.color1)
        color2 = Singleton.sharedPref.getString(AppConstants.color2)
        listAdapter.updateValues(listSize, color1, color2)
        binding.lvList.adapter =listAdapter

    }

    fun onButtonFab() {
        dialog = Dialog(this)
        dialogBinding = DialogBoxBinding.inflate(layoutInflater)
        dialogBinding.colorPicker=this
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.show()
        dialogBinding.etColor2.setText(Singleton.sharedPref.getString(AppConstants.color2))
        dialogBinding.etColor1.setText(Singleton.sharedPref.getString(AppConstants.color1))
        dialogBinding.etSize.setText(Singleton.sharedPref.getInt(AppConstants.listSize).toString())

    }

    fun onButtonColor1(){
        ColorPickerDialog
            .Builder(this)        				// Pass Activity Instance
            .setTitle("Pick Theme")           	// Default "Choose Color"
            .setColorShape(ColorShape.SQAURE)   // Default ColorShape.CIRCLE
            .setDefaultColor(R.color.white)     // Pass Default Color
            .setColorListener { color, colorHex ->
                // Handle Color Selection
                color1 =colorHex
                dialogBinding.etColor1.setText(colorHex)
                Singleton.sharedPref.saveString(AppConstants.color1,colorHex )
            }
            .show()
    }
    fun onButtonColor2(){
        ColorPickerDialog
            .Builder(this)        				// Pass Activity Instance
            .setTitle("Pick Theme")           	// Default "Choose Color"
            .setColorShape(ColorShape.SQAURE)   // Default ColorShape.CIRCLE
            .setDefaultColor(R.color.white)     // Pass Default Color
            .setColorListener { color, colorHex ->
                // Handle Color Selection
                dialogBinding.etColor2.setText(colorHex)
                color2 = colorHex
                Singleton.sharedPref.saveString(AppConstants.color2,colorHex )
            }
            .show()

    }
    fun onButtonSave(){
        listSize=dialogBinding.etSize.text.toString().toInt()
        Singleton.sharedPref.saveInt(AppConstants.listSize, dialogBinding.etSize.text.toString().toInt())
        listAdapter.updateValues(listSize, color1, color2)
        listAdapter.notifyDataSetChanged()
        dialog.dismiss()

    }
    fun onButtonClear(){
        Singleton.sharedPref.clearPrefs()
        listSize=0
        listAdapter.updateValues(listSize, color1, color2)
        listAdapter.notifyDataSetChanged()

    }
}

