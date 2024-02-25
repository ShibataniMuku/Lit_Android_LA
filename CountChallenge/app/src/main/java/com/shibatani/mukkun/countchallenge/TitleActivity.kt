package com.shibatani.mukkun.countchallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.shibatani.mukkun.countchallenge.databinding.ActivityMainBinding
import com.shibatani.mukkun.countchallenge.databinding.ActivityTitleBinding

class TitleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTitleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTitleBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        binding.goMainButton.setOnClickListener {
            val mainIntent: Intent = Intent(this, MainActivity::class.java)
            startActivity(mainIntent)
        }
    }
}