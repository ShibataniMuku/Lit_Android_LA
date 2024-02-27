package com.shibatani.mukkun.product_memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // BookListFragment表示処理
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_activity_main, ItemListFragment())
            .commit()
    }
}