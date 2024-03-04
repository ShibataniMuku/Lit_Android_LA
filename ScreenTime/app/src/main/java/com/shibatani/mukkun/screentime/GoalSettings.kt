package com.shibatani.mukkun.screentime

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.shibatani.mukkun.screentime.databinding.ActivityGoalSettingsBinding
import com.shibatani.mukkun.screentime.databinding.ActivityMainBinding

class GoalSettings : AppCompatActivity() {
    private lateinit var binding: ActivityGoalSettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_settings)
        binding = ActivityGoalSettingsBinding.inflate(layoutInflater).apply { setContentView(this.root) }
        val pref: SharedPreferences = getSharedPreferences("SharedPref", Context.MODE_PRIVATE)

        // 設定ボタンが押されたら
        binding.completeButton.setOnClickListener {
            val editedHour = binding.hourEditText.text.toString()
            val editedMinis = binding.minitsEditText.text.toString()

            if(editedHour != "" && editedMinis != ""){
                val mainActivity = Intent(this, MainActivity::class.java)

                val editor = pref.edit()
                editor.putInt("GoalHour", editedHour.toInt())
                editor.putInt("GoalMinis", editedMinis.toInt())
                editor.apply()

                startActivity(mainActivity)
                finish()
            }
        }
    }
}