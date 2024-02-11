package com.shibatani.mukkun.profile

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.shibatani.mukkun.profile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    // パディングクラスの変数
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }



        binding.showProfileButton.setOnClickListener{
            // 画像をImageViewに反映する
            binding.profileImage.setImageResource(R.drawable.randy_image)
            // labelをTextViewに反映する
            binding.profileLabelText.text = "名前"
            // コメントをTextViewに反映する
            binding.profileCommentText.text = "Androidメンターのランディだよ"

            binding.showProfileButton.backgroundTintList =
                ColorStateList.valueOf(Color.rgb(0, 180, 220))
            binding.showSportButton.backgroundTintList =
                ColorStateList.valueOf(Color.rgb(150, 150, 150))
            binding.showFoodButton.backgroundTintList =
                ColorStateList.valueOf(Color.rgb(150, 150, 150))
            binding.showHobbyButton.backgroundTintList =
                ColorStateList.valueOf(Color.rgb(150, 150, 150))
        }

        binding.showSportButton.setOnClickListener{
            binding.profileImage.setImageResource(R.drawable.baseball_image)
            binding.profileLabelText.text = "スポーツ"
            binding.profileCommentText.text = "野球観戦が好きで、スタジアムに通っている"

            binding.showProfileButton.backgroundTintList =
                ColorStateList.valueOf(Color.rgb(150, 150, 150))
            binding.showSportButton.backgroundTintList =
                ColorStateList.valueOf(Color.rgb(220, 0, 100))
            binding.showFoodButton.backgroundTintList =
                ColorStateList.valueOf(Color.rgb(150, 150, 150))
            binding.showHobbyButton.backgroundTintList =
                ColorStateList.valueOf(Color.rgb(150, 150, 150))
        }

        binding.showFoodButton.setOnClickListener {
            binding.profileImage.setImageResource(R.drawable.donut_image)
            binding.profileLabelText.text = "好きな食べ物"
            binding.profileCommentText.text = "キャンディやドーナツが大好き"

            binding.showProfileButton.backgroundTintList =
                ColorStateList.valueOf(Color.rgb(150, 150, 150))
            binding.showSportButton.backgroundTintList =
                ColorStateList.valueOf(Color.rgb(150, 150, 150))
            binding.showFoodButton.backgroundTintList =
                ColorStateList.valueOf(Color.rgb(0, 180, 120))
            binding.showHobbyButton.backgroundTintList =
                ColorStateList.valueOf(Color.rgb(150, 150, 150))
        }

        binding.showHobbyButton.setOnClickListener {
            binding.profileImage.setImageResource(R.drawable.gadget_image)
            binding.profileLabelText.text = "趣味"
            binding.profileCommentText.text = "ガジェットを集めて動かすこと"

            binding.showProfileButton.backgroundTintList =
                ColorStateList.valueOf(Color.rgb(150, 150, 150))
            binding.showSportButton.backgroundTintList =
                ColorStateList.valueOf(Color.rgb(150, 150, 150))
            binding.showFoodButton.backgroundTintList =
                ColorStateList.valueOf(Color.rgb(150, 150, 150))
            binding.showHobbyButton.backgroundTintList =
                ColorStateList.valueOf(Color.rgb(220, 100, 0))
        }
    }
}