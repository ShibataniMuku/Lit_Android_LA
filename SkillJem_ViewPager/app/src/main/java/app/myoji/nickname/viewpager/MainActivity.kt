package app.myoji.nickname.viewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.myoji.nickname.viewpager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // バインディングクラスの変数
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

    }
}