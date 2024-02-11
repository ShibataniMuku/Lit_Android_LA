package com.shibatani.mukkun.sound

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.shibatani.mukkun.sound.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        // ドラムのサウンドファイルを読み込んで、プレイヤーを作る
        val drumSound: MediaPlayer = MediaPlayer.create(this, R.raw.drum_sound)

        // ドラムがタップされたときの処理
        binding.drumImage.setOnTouchListener{ view, motionEvent ->
            if(motionEvent.action == MotionEvent.ACTION_DOWN){
                binding.drumImage.setImageResource(R.drawable.drum_playing_image)
                drumSound.seekTo(0)
                drumSound.start()
            } else if(motionEvent.action == MotionEvent.ACTION_UP){
                // ドラムが鳴っていない画像に切り替える
                binding.drumImage.setImageResource(R.drawable.drum_image)
            }

            true
        }

        val pianoSound: MediaPlayer = MediaPlayer.create(this, R.raw.piano_sound)

        binding.pianoImage.setOnTouchListener{ view, motionEvent ->
            if(motionEvent.action == MotionEvent.ACTION_DOWN){
                binding.pianoImage.setImageResource(R.drawable.piano_playing_image)
                pianoSound.seekTo(0)
                pianoSound.start()
            } else if(motionEvent.action == MotionEvent.ACTION_UP){
                binding.pianoImage.setImageResource(R.drawable.piano_image)
            }

            true
        }

        var guitarSound: MediaPlayer = MediaPlayer.create(this, R.raw.guitar_sound)

        binding.guitarImage.setOnTouchListener{ view, motionEvent ->
            if(motionEvent.action == MotionEvent.ACTION_DOWN){
                binding.guitarImage.setImageResource(R.drawable.guitar_playing_image)
                guitarSound.seekTo(0)
                guitarSound.start()
            } else if(motionEvent.action == MotionEvent.ACTION_UP){
                binding.guitarImage.setImageResource(R.drawable.guitar_image)
            }

            true
        }
    }
}