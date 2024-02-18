package jp.codeforfun.catchtheball

import android.R
import android.content.Context
import android.media.AudioManager
import android.media.SoundPool

class SoundPlayer(context: Context?) {
    init {
        soundPool = SoundPool(2, AudioManager.STREAM_MUSIC, 0)
        overSound = soundPool.load(context, R.raw.over, 1)
    }

    fun playOverSound() {
        soundPool.play(overSound, 1.0f, 1.0f, 1, 0, 1.0f)
    }

    companion object {
        private lateinit var soundPool: SoundPool
        private var overSound: Int = 0
    }
}