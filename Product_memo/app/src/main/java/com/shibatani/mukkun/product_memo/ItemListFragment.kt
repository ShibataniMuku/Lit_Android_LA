package com.shibatani.mukkun.product_memo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener

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
        var addedTitle: String = ""
        var addedContent: String = ""

        // 追加画面から渡されたアイテムデータをもとに追加して表示する
        setFragmentResultListener("itemAddedData") { _, bundle ->
            addedTitle = bundle.getString("itemTitle").toString()
            addedContent = bundle.getString("itemContent").toString()

            if(addedTitle != "" && addedContent != ""){
                bookList.add(Item(addedTitle, addedContent, bookList.count()))
                Log.d("debug", "新しいアイテムを追加しました")
            }
        }

        Log.d("debug", "新しいアイテム作成します")

        // 20件のダミーデータを登録
//        var i = 0
//        while (i < 20) {
//            i++
//            bookList.add(book)
//        }
        return bookList
    }
}
