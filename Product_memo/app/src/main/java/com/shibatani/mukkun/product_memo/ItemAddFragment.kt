package com.shibatani.mukkun.product_memo

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ItemAddFragment : Fragment(){
    var title: String = ""
    var content: String = ""
    var index: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_add, container, false)

        activity?.title = "メモの編集"

        view.findViewById<Button>(R.id.complete_button).setOnClickListener {
            title = view.findViewById<EditText>(R.id.add_title_text).text.toString()
            content = view.findViewById<EditText>(R.id.add_content_text).text.toString()

            if(title != "" && content != ""){
                // データの読み込み
                var bookList: MutableList<Item> = ArrayList()
                val gson = Gson()
                val pref: SharedPreferences = requireContext().getSharedPreferences("pref", MODE_PRIVATE)
                val json = pref.getString("ItemList", null)
                if (json != null) {
                    bookList = gson.fromJson<ArrayList<Item>>(
                        json,
                        object : TypeToken<ArrayList<Item?>?>() {}.type
                    )
                }
                // データの追加
                bookList.add(Item(title, content, bookList.count()))
                // データの保存
                pref.edit().putString("ItemList", gson.toJson(bookList)).apply()

                // ホーム画面への遷移処理
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fl_activity_main, ItemListFragment())
                    .addToBackStack(null)
                    .commit()

            }
        }

        return view
    }
}