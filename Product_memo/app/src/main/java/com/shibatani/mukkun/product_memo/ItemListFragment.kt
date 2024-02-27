package com.shibatani.mukkun.product_memo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.Fragment

class ItemListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        // タイトルをセット
        activity?.title = "書籍情報一覧"

        // レイアウト要素RecyclerViewを取得
        val bookListRecyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        // LayoutManagerを取得
        val linearLayoutManager = LinearLayoutManager(view.context)
        // ダミーデータをセットしたアダプターを作成
        val adapter = ItemListRecyclerViewAdapter(createDummyBookList())

        // linearLayoutManager と adapter をRecyclerViewにセット
        bookListRecyclerView.layoutManager = linearLayoutManager
        bookListRecyclerView.adapter = adapter

        // 一覧画面の各セルの区切り線を作成
        bookListRecyclerView.addItemDecoration(DividerItemDecoration(view.context, linearLayoutManager.orientation))

        return view
    }

    // アダプターにセットするためのサンプルデータを作成するメソッド
    private fun createDummyBookList(): MutableList<Item> {
        var itemList: MutableList<Item> = ArrayList()
        var book = Item("Kotlinスタートブック", 2800, "2020/11/24")

        // 20件のサンプルーデータを登録
        var i = 0
        while (i < 20) {
            i++
            itemList.add(book)
        }
        itemList.add(Item("AndroidStudioスタートブック", 2800, "2024/02/27"))
        return itemList
    }
}
