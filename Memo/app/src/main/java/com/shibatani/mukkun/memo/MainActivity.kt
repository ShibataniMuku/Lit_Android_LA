package com.shibatani.mukkun.memo

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shibatani.mukkun.memo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    //var recyclerView = findViewById<RecyclerView>(R.id.memo_list)

    // @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        //var recyclerView = findViewById<RecyclerView>(R.id.memo_list)

//        // RecyclerViewのインスタンスを取得
//        val rcySample = findViewById<RecyclerView>(R.id.rcySample)
//        // RecyclerViewにレイアウトマネージャーを設定（必要に応じて）
//        rcySample.layoutManager = LinearLayoutManager(this)
//        // アダプターをセット（必要に応じて）
//        val adapter = ListAdapter()
//        rcySample.adapter = adapter

        binding.addMemoButton.setOnClickListener {
            if(binding.editMemoText.text.toString() == "") return@setOnClickListener
            var addedMemo:String = binding.editMemoText.text.toString()
            insert(0, addedMemo)
            binding.editMemoText.setText("")
        }

        //RecyclerViewの取得
        val recyclerView = findViewById<RecyclerView>(R.id.memo_list)

        //Adapterの設定
        val adapter = ItemAdapter()
        recyclerView.adapter = adapter

        //LayoutManagerの設定
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
    }

    private fun insert(position: Int, item: String) {
        var recyclerView = findViewById<RecyclerView>(R.id.memo_list)
        val _adapter = recyclerView.adapter as ItemAdapter
        _adapter.data.add(position, item) // データの変更
        _adapter.notifyItemInserted(position) // 変更の通知（表示を更新）
    }
}