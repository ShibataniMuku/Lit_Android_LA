package com.shibatani.mukkun.skilljem_sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shibatani.mukkun.skilljem_sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val pref: SharedPreferences = getSharedPreferences("SharedPref", Context.MODE_PRIVATE)

        binding.saveButton.setOnClickListener {
            val stringText = binding.editText.text.toString()
            binding.saveText.text = stringText

            val editor = pref.edit()
            editor.putString("DataKey", stringText)
            editor.apply()
        }

        binding.readButton.setOnClickListener {
            val str = pref.getString("DataKey", "NoData")
            binding.readText.text = str
        }
    }
}