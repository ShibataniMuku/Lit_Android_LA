package com.shibatani.mukkun.product_memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.shibatani.mukkun.product_memo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).apply { setContentView(this.root) }
        setContentView(R.layout.activity_main)

        // BookListFragment表示処理
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_activity_main, ItemListFragment())
            .commit()
    }
}