package com.shibatani.mukkun.album

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shibatani.mukkun.album.databinding.ActivityPreviewBinding

class PreviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewBinding.inflate(layoutInflater).apply{setContentView(this.root)}

        // 遷移前の画面から、写真をタイトルを受け取る
        val imageId = intent.getIntExtra("image", 0)
        val displayTitle = intent.getStringExtra("title")

        // 受け取った情報をセット
        binding.previewImage.setImageResource(imageId)
        binding.titleText.text = displayTitle

        // 戻るボタンがタップされたとき
        binding.backButton.setOnClickListener {
            finish()
        }
    }
}