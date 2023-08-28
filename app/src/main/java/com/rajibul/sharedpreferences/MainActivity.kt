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
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.activity = this
        Singleton.sharedPref.initPrefs(this)

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
        dialogBinding.etColor2.setText(Singleton.sharedPref.getString(AppConstants.color2))
        dialogBinding.etColor1.setText(Singleton.sharedPref.getString(AppConstants.color1))
        dialogBinding.etSize.setText(Singleton.sharedPref.getString(AppConstants.listSize))

        dialogBinding.etColor1.setOnClickListener{
            ColorPickerDialog
                .Builder(this)        				// Pass Activity Instance
                .setTitle("Pick Theme")           	// Default "Choose Color"
                .setColorShape(ColorShape.SQAURE)   // Default ColorShape.CIRCLE
                .setDefaultColor(R.color.white)     // Pass Default Color
                .setColorListener { color, colorHex ->
                    // Handle Color Selection

                    dialogBinding.etColor1.setText(colorHex)
                    Singleton.sharedPref.saveString(AppConstants.color1,colorHex )
                }
                .show()

        }
        dialogBinding.etColor2.setOnClickListener{
            ColorPickerDialog
                .Builder(this)        				// Pass Activity Instance
                .setTitle("Pick Theme")           	// Default "Choose Color"
                .setColorShape(ColorShape.SQAURE)   // Default ColorShape.CIRCLE
                .setDefaultColor(R.color.white)     // Pass Default Color
                .setColorListener { color, colorHex ->
                    // Handle Color Selection
                    dialogBinding.etColor2.setText(colorHex)
                    Singleton.sharedPref.saveString(AppConstants.color2,colorHex )
                }
                .show()
        }
        
        dialogBinding.btnSave.setOnClickListener {
            Singleton.sharedPref.saveString(AppConstants.listSize, dialogBinding.etSize.text.toString())
            dialog.dismiss()
        }
    }

    }

