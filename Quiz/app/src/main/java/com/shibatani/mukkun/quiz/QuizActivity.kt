package com.shibatani.mukkun.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import com.shibatani.mukkun.quiz.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    val quizLists: List<List<String>> = listOf(
        listOf("Androidコースのキャラクターの名前は？", "ランディ", "フィル", "ドロイド", "ランディ"),
        listOf("Androidを開発する言語はどれ？", "JavaScripts", "Kotlin", "Swift", "Kotlin"),
        listOf("ImageViewは、何を扱える？", "文字", "音声", "画像", "画像")
    )

    val shuffledLists: List<List<String>> = quizLists.shuffled()
    var quizCount: Int = 0
    // 正解の回数
    var correctCount: Int = 0
    // 正解の選択肢
    var correctAnswer: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        showQuestion()

        binding.answerButton1.setOnClickListener {
            checkAnswer(binding.answerButton1.text.toString())
        }

        binding.answerButton2.setOnClickListener {
            checkAnswer(binding.answerButton2.text.toString())
        }

        binding.answerButton3.setOnClickListener {
            checkAnswer(binding.answerButton3.text.toString())
        }

        binding.nextButton.setOnClickListener {
            if(quizCount == quizLists.size){
                val resultIntent: Intent = Intent(this, ResultActivity::class.java)
                resultIntent.putExtra("QuizCount", quizLists.size)
                resultIntent.putExtra("CorrectCount", correctCount)
                startActivity(resultIntent)
            } else {
                binding.judgeImage.isVisible = false
                binding.nextButton.isVisible = false

                binding.answerButton1.isEnabled = true
                binding.answerButton2.isEnabled = true
                binding.answerButton3.isEnabled = true

                binding.correctAnswerText.text = ""

                showQuestion()
            }
        }
    }

    fun showQuestion(){
        val question: List<String> = shuffledLists[quizCount]

        Log.d("debug", question.toString())

        binding.quizText.text = question[0]

        binding.answerButton1.text = question[1]
        binding.answerButton2.text = question[2]
        binding.answerButton3.text = question[3]

        correctAnswer = question[4]
    }

    fun checkAnswer(answertext: String){
        if(answertext == correctAnswer){
            // 正解
            binding.judgeImage.setImageResource(R.drawable.maru_image)
            correctCount++
        } else {
            binding.judgeImage.setImageResource(R.drawable.batu_image)
        }

        // マルバツ画像を表示
        showAnswer()
        quizCount++
    }

    fun showAnswer(){
        binding.correctAnswerText.text = "正解：$correctAnswer"
        binding.judgeImage.isVisible = true
        binding.nextButton.isVisible = true

        binding.answerButton1.isEnabled = false
        binding.answerButton2.isEnabled = false
        binding.answerButton3.isEnabled = false
    }
}