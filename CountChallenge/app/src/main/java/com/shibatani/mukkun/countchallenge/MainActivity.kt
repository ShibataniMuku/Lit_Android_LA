package com.shibatani.mukkun.countchallenge

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shibatani.mukkun.countchallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var count: Int = 0
    val countPref: SharedPreferences = getSharedPreferences("SharedPref", Context.MODE_PRIVATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        count = countPref.getInt("Count", 0)
        binding.countText.text = count.toString()
        binding.countButton.setOnClickListener {
            count++
            binding.countText.text = count.toString()

            when(count % 2){
                0 -> binding.countText.setTextColor(Color.BLUE)
                1 -> binding.countText.setTextColor(Color.RED)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

//        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
//        SharedPreferences.Editor editor = sharedPreferences.edit();
        val editor = countPref.edit()
        editor.putInt("Count", count);
        editor.apply(); // 変更を保存する
    }
}