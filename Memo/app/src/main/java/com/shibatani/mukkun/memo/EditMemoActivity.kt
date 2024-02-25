package com.shibatani.mukkun.memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.shibatani.mukkun.memo.databinding.ActivityEditMemoBinding
import com.shibatani.mukkun.memo.databinding.ActivityMainBinding

class EditMemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditMemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_memo)
        binding = ActivityEditMemoBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        // 保存ボタン
        binding.addMemoButton.setOnClickListener {
            var addedText: String = binding.editText.text.toString()
            if(addedText.isNotEmpty()){
                val mainActivity = Intent(this, MainActivity::class.java)

                mainActivity.putExtra("MemoText", addedText)
                startActivity(mainActivity)
                finish()
            }
        }

        // 削除ボタン
        binding.deleteMemoButton.setOnClickListener {

        }
    }
}