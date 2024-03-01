package com.shibatani.mukkun.product_memo

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class ItemListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // タイトルをセット
        activity?.title = "メモ一覧"

        // レイアウト要素RecyclerViewを取得
        val bookListRecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        // LayoutManagerを取得
        val linearLayoutManager = LinearLayoutManager(view.context)
        // ダミーのデータを作成
        val dummyBookList = createDummyBookList()
        // ダミーデータをセットしたアダプターを作成
        val adapter = ItemListRecyclerViewAdapter(dummyBookList)

        // linearLayoutManager と adapter をRecyclerViewにセット
        bookListRecyclerView.layoutManager = linearLayoutManager
        bookListRecyclerView.adapter = adapter

        // 一覧画面の各セルの区切り線を作成
        bookListRecyclerView.addItemDecoration(DividerItemDecoration(view.context, linearLayoutManager.orientation))

        // 書籍情報セルのクリック処理
        adapter.setOnBookCellClickListener(
            // インターフェースの再利用は想定しておらず、その場限りでしか使わないためobject式として宣言
            object : ItemListRecyclerViewAdapter.OnBookCellClickListener {
                override fun onItemClick(book: Item) {
                    // 書籍データを渡す処理
                    setFragmentResult("itemData", bundleOf(
                        "itemTitle" to book.title,
                        "itemContent" to book.content,
                        "itemIndex" to book.index
                    ))

                    // 詳細画面への遷移処理
                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.fl_activity_main, ItemFragment())
                        .addToBackStack(null)
                        .commit()
                }
            }
        )

        view.findViewById<Button>(R.id.add_button).setOnClickListener {
//            // アイテムデータを追加画面へ渡す処理
//            setFragmentResult("itemAddedData", bundleOf(
//                "itemTitle" to "",
//                "itemContent" to "",
//            ))

            // 編集画面への遷移処理
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fl_activity_main, ItemAddFragment())
                .addToBackStack(null)
                .commit()
        }

        return view
    }

    // サンプルデータ作成メソッド
    private fun createDummyBookList(): MutableList<Item> {
        var bookList: MutableList<Item> = ArrayList()
        var book = Item("メモのタイトル", "メモの本文", 0)

        // データの読み込み
        val pref: SharedPreferences = requireContext().getSharedPreferences("pref", MODE_PRIVATE)
        val json = pref.getString("ItemList", null)
        if (json != null) {
            val gson = Gson()
            bookList = gson.fromJson<ArrayList<Item>>(
                json,
                object : TypeToken<ArrayList<Item?>?>() {}.type
            )
        }

        return bookList
    }
}
