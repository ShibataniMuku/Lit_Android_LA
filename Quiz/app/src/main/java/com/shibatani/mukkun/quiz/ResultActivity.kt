package com.shibatani.mukkun.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shibatani.mukkun.quiz.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    // バインディングクラスの変数
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        binding = ActivityResultBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val quizCount: Int = intent.getIntExtra("QuizCount", 0)
        binding.quizCountText.text = "$quizCount 問中..."

        val correctCount: Int = intent.getIntExtra("CorrectCount", 0)
        binding.correctCountText.text = correctCount.toString()

        binding.againButton.setOnClickListener {
            val quizIntent: Intent = Intent(this, QuizActivity::class.java)
            startActivity(quizIntent)
        }
    }
}