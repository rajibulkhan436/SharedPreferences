package com.rajibul.sharedpreferences

import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.rajibul.sharedpreferences.databinding.ActivityMainBinding
import com.rajibul.sharedpreferences.databinding.DialogBoxBinding

class MainActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreferences= getSharedPreferences(resources.getString(R.string.app_name), Context.MODE_PRIVATE)
        editor= sharedPreferences.edit()
        binding.activity = this
    }
    fun onButtonFab() {
        var dialog = Dialog(this)
        var dialogBinding = DialogBoxBinding.inflate(layoutInflater)
        dialog.setContentView(dialogBinding.root)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.show()

        dialogBinding.btnSave.setOnClickListener {
            dialog.dismiss()
        }
    }

        fun onButtonColor1(){
            ColorPickerDialog
                .Builder(this)        				// Pass Activity Instance
                .setTitle("Pick Theme")           	// Default "Choose Color"
                .setColorShape(ColorShape.SQAURE)   // Default ColorShape.CIRCLE
                .setDefaultColor(R.color.white)     // Pass Default Color
                .setColorListener { color, colorHex ->
                    // Handle Color Selection
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
            }
            .show()

    }

    }

