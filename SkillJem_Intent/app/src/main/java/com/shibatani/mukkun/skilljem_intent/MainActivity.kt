package com.shibatani.mukkun.skilljem_intent

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.shibatani.mukkun.skilljem_intent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        binding.intentButton.setOnClickListener {
            val toSecondActivityIntent = Intent(this, SecondActivity::class.java)
            startActivity(toSecondActivityIntent)
        }

        binding.playStoreButton.setOnClickListener {
            val pm = packageManager
            val playStoreIntent = pm.getLaunchIntentForPackage("com.android.vending")

            startActivity(playStoreIntent)
        }

        binding.mapButton.setOnClickListener {
            // intentを作る
            val mapIntent = Intent(Intent.ACTION_VIEW)
            // 地図アプリで表示させたい座標をintentに設定
            mapIntent.data = Uri.parse("geo:35.6473,139.7360")
            // intentを使って画面遷移
            if(mapIntent.resolveActivity(packageManager) != null){
                startActivity(mapIntent)
            }
        }

        binding.browserButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW)
             browserIntent.data = Uri.parse("https://Life-is-tech.com")

            if(browserIntent.resolveActivity(packageManager) != null){
                startActivity(browserIntent)
            }
        }

        binding.galleryButton.setOnClickListener {
            getImageContent.launch("image/*")
        }
    }

    private val getImageContent =
        registerForActivityResult(ActivityResultContracts.GetContent()){uri: Uri? ->
            binding.imageView.setImageURI(uri)
        }
}