package com.shibatani.mukkun.product_memo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult

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
        // ダミーデータをセットしたアダプターを作成
        val adapter = ItemListRecyclerViewAdapter(createDummyBookList())

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
                        //"bookPurchaseDate" to book.date
                    ))

                    // 画面遷移処理
                    parentFragmentManager
                        .beginTransaction()
                        .replace(R.id.fl_activity_main, ItemFragment())
                        .addToBackStack(null)
                        .commit()
                }
            }
        )

        return view
    }

    // サンプルデータ作成メソッド
    private fun createDummyBookList(): MutableList<Item> {
        var bookList: MutableList<Item> = ArrayList()
        var book = Item("メモのタイトル", "メモの本文")

        // 20件のダミーデータを登録
        var i = 0
        while (i < 20) {
            i++
            bookList.add(book)
        }
        return bookList
    }
}
