package com.shibatani.mukkun.renda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.shibatani.mukkun.renda.databinding.ActivityMainBinding
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    // バインディングクラスの変数
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply {setContentView(this.root)}

        // タップを数える変数を準備する
        var tapCount: Int = 0
        // 残り秒数を１０秒にセット
        var second = 10

        var timer: CountDownTimer = object : CountDownTimer(10000, 10000){
            // タイマーが終了するときに呼ばれる
            override fun onFinish() {
                // STARTボタンが見える状態にする
                binding.startButton.isVisible = true
                // TAPボタンを灰色にセットする
                binding.tapButton.backgroundTintList =
                    ContextCompat.getColorStateList(this@MainActivity, R.color.gray)
                // 残り時間を１０秒に戻す
                second = 10
                // タップを数える変数を０に戻す
                tapCount = 0
            }

            // １秒ごとに呼ばれる
            override fun onTick(millisUntilFinished: Long) {
                // TAPボタンをピンク色にセット
                binding.tapButton.backgroundTintList =
                    ContextCompat.getColorStateList(this@MainActivity, R.color.pink)
                // 残り秒数をマイナス１する
                second = second - 1
                // 残り秒数をTextViewに反映する
                binding.secondText.text = second.toString()
            }
        }

        // STARTボタンがタップされたときの処理
        binding.startButton.setOnClickListener{
            binding.countText.text = tapCount.toString()
            binding.startButton.isVisible = false
            // タイマーを開始する
            timer.start()
        }

        // ボタンがタップされたときの処理
        binding.tapButton.setOnClickListener{
            if(second < 10){
                // タップを数える変数にプラス1する
                tapCount = tapCount + 1

                // タップされた数をテキストビューに反映する
                binding.countText.text = tapCount.toString()
            }
        }
    }
}