package com.shibatani.mukkun.skilljem_button

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shibatani.mukkun.skilljem_button.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply{setContentView(this.root)}

        binding.button.setOnClickListener {  }
    }
}