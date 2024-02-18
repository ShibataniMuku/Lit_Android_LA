package com.shibatani.mukkun.countchallenge

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shibatani.mukkun.countchallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        var count: Int = 0

        binding.countButton.setOnClickListener {
            count++
            when(count % 2){
                0 -> binding.countText.setTextColor(Color.parseColor("#FF0000"))
                1 -> binding.countText.setTextColor(Color.parseColor("#00FF00"))
            }

            binding.countText.text = count.toString()
        }
    }
}