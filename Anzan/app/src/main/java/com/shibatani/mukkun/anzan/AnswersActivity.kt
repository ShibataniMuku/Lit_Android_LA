package com.shibatani.mukkun.anzan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shibatani.mukkun.anzan.databinding.ActivityAnswersBinding
import com.shibatani.mukkun.anzan.databinding.ActivityMainBinding

class AnswersActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnswersBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answers)
        binding = ActivityAnswersBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        val displayQuestion = intent.getStringExtra("question")
        val yourAnswer = intent.getStringExtra("answer")
        val correctAnswer = intent.getStringExtra("correct")

        binding.questionText.text = displayQuestion
        binding.yourAnswerText.text = yourAnswer

        if(yourAnswer == correctAnswer){
            binding.markImage.setImageResource(R.drawable.correct_image)
            binding.randyImage.setImageResource(R.drawable.randy_happy_image)
        }
        else
        {
            binding.markImage.setImageResource(R.drawable.miss_image)
            binding.randyImage.setImageResource(R.drawable.randy_sad_image)
        }

        binding.backButton.setOnClickListener {
            val questionPage = Intent(this, MainActivity::class.java)
            startActivity(questionPage)
            finish()
        }
    }
}