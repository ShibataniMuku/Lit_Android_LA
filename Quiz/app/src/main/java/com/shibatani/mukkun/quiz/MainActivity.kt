package com.shibatani.mukkun.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shibatani.mukkun.quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply{ setContentView((this.root))}

        // クイズ画面へ移動する準備をする
        val quizIntent: Intent = Intent(this, QuizActivity::class.java)

        // STARTボタンが押されたら
        binding.startButton.setOnClickListener {
            // クイズ画面へ遷移する
            startActivity(quizIntent)
        }
    }
}