package com.shibatani.mukkun.memo

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shibatani.mukkun.memo.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView

    // @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }

        //RecyclerViewの取得
        recyclerView = findViewById<RecyclerView>(R.id.memo_list)
        //Adapterの設定
        val adapter = ItemAdapter()
        recyclerView.adapter = adapter
        //LayoutManagerの設定
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        // RecyclerViewにクリック判定を付与
        val itemTouchListener = ItemTouchListener()
        recyclerView.addOnItemTouchListener(itemTouchListener)

        // 保存されているデータを書き出し
        val sharedPreferences = getSharedPreferences("memo_data", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("memo_list", "")

        if (json != null && json.isNotEmpty()) {
            val type = object : TypeToken<MutableList<String>>() {}.type
            val mutableList: MutableList<String> = gson.fromJson(json, type)

            // メモを追加する
            val addedText = mutableList
            Log.d("debug", "データを書き出します")
            if(addedText.isNotEmpty()){
                for(i in mutableList.count() - 1 downTo 0){
                    insert(0, mutableList[i])
                }
            }
        }

        // メモを追加ボタンが押されたとき
        binding.editMemoButton.setOnClickListener {
            val editMemoActivity = Intent(this, EditMemoActivity::class.java)
            startActivity(editMemoActivity)
        }
    }

    override fun onResume() {
        // このActivityがアクティブになると呼ばれる
        super.onResume()
        val addedText = intent.getStringExtra("MemoText").toString()

        if(addedText.isNotEmpty()){
            insert(0, addedText)
        }
    }

    override fun onStop() {
        // アプリが終了すると呼ばれる
        super.onStop()
        val _adapter = recyclerView.adapter as ItemAdapter
        val sharedPreferences = getSharedPreferences("memo_data", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(_adapter.data)
        editor.putString("memo_list", json)
        editor.apply()

        //sharedPreferences.edit().remove("memo_list").apply()
    }

    private fun insert(position: Int, item: String) {
        //var recyclerView = findViewById<RecyclerView>(R.id.memo_list)
        val _adapter = recyclerView.adapter as ItemAdapter
        _adapter.data.add(position, item) // データの変更
        _adapter.notifyItemInserted(position) // 変更の通知（表示を更新）
    }
}

class ItemTouchListener : RecyclerView.SimpleOnItemTouchListener() {
    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        // タッチイベントの処理
        if (e.action == MotionEvent.ACTION_DOWN) {
            // タッチされた位置のViewを取得
            val childView: View? = rv.findChildViewUnder(e.x, e.y)
            if (childView != null) {
                // 要素番号を取得
                val position = rv.getChildAdapterPosition(childView)
                if (position != RecyclerView.NO_POSITION) {
                    val adapter = rv.adapter
                    if (adapter is ItemAdapter) {
                        val tappedItem: String? = adapter.getItemAtPosition(position)
                        if (tappedItem != null) {
                            // この中に、RecyclerViewの要素がタップされた際の処理を記述
                            //Toast.makeText(rv.context, tappedItem.toString(), Toast.LENGTH_LONG).show()

                        }
                    }
                }
            }
        }
        return false // 通常のタッチイベント処理を維持
    }
}
