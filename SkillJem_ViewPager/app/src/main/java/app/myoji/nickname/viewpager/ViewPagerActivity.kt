package app.myoji.nickname.viewpager

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import app.myoji.nickname.viewpager.databinding.ActivityViewPagerBinding

class ViewPagerActivity : AppCompatActivity() {

    // バインディングクラスの変数
    private lateinit var binding: ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }

    }
}