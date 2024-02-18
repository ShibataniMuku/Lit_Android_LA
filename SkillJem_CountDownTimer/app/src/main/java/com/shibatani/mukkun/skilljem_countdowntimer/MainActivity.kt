package com.shibatani.mukkun.skilljem_countdowntimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.core.view.isVisible
import com.shibatani.mukkun.skilljem_countdowntimer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        var second = 10

        val timer: CountDownTimer = object : CountDownTimer(10000, 1000){
            override fun onFinish() {
                binding.startButton.isVisible = true
                second = 10
                binding.secondText.text = second.toString()
            }

            override fun onTick(millisUntilFinished: Long) {
                second--
                binding.secondText.text = second.toString()
            }
        }

        binding.secondText.text = second.toString()

        binding.startButton.setOnClickListener {
            binding.startButton.isVisible = false

            timer.start()
        }
    }
}