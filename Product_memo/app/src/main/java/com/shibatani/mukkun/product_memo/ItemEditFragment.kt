package com.shibatani.mukkun.product_memo

import android.content.Context
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
import androidx.fragment.app.setFragmentResultListener
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ItemEditFragment : Fragment(){
    lateinit var title: String
    lateinit var content: String
    var index: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_edit, container, false)

        activity?.title = "メモの編集"

        // 詳細画面から渡されたデータをviewに表示する
        setFragmentResultListener("itemEditedData") { _, bundle ->
            title = bundle.getString("itemTitle").toString()
            content = bundle.getString("itemContent").toString()
            index = bundle.getInt("itemIndex")
            view.findViewById<EditText>(R.id.edit_title_text).setText(title)
            view.findViewById<EditText>(R.id.edit_content_text).setText(content)
        }

        view.findViewById<Button>(R.id.complete_button).setOnClickListener {
            var newTitle = view.findViewById<EditText>(R.id.edit_title_text).text
            var newContent = view.findViewById<EditText>(R.id.edit_content_text).text

            // データの読み込み
            var bookList: MutableList<Item> = ArrayList()
            val gson = Gson()
            val pref: SharedPreferences = requireContext().getSharedPreferences("pref",
                Context.MODE_PRIVATE
            )
            val json = pref.getString("ItemList", null)
            if (json != null) {
                bookList = gson.fromJson<ArrayList<Item>>(
                    json,
                    object : TypeToken<ArrayList<Item?>?>() {}.type
                )
            }
            // データの編集
            bookList[index].title = newTitle.toString()
            bookList[index].content = newContent.toString()
            // データの保存
            pref.edit().putString("ItemList", gson.toJson(bookList)).apply()

            // ホーム画面への遷移処理
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fl_activity_main, ItemListFragment())
                .addToBackStack(null)
                .commit()
        }

        view.findViewById<Button>(R.id.delete_button).setOnClickListener {
            // データの読み込み
            var bookList: MutableList<Item> = ArrayList()
            val gson = Gson()
            val pref: SharedPreferences = requireContext().getSharedPreferences("pref",
                Context.MODE_PRIVATE
            )
            val json = pref.getString("ItemList", null)
            if (json != null) {
                bookList = gson.fromJson<ArrayList<Item>>(
                    json,
                    object : TypeToken<ArrayList<Item?>?>() {}.type
                )
            }
            // データの編集
            bookList.removeAt(index)
            for(i in 0 .. bookList.count() - 1){
                bookList[i].index
            }
            // データの保存
            pref.edit().putString("ItemList", gson.toJson(bookList)).apply()

            // ホーム画面への遷移処理
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fl_activity_main, ItemListFragment())
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}