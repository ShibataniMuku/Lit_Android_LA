package com.shibatani.mukkun.product_memo

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import kotlin.math.log

class ItemEditFragment : Fragment(){
    lateinit var title: String
    lateinit var content: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_edit, container, false)

        activity?.title = "メモの編集"

        // 一覧画面から渡されたデータをviewに表示する
        setFragmentResultListener("itemEditedData") { _, bundle ->
            title = bundle.getString("itemTitle").toString()
            content = bundle.getString("itemContent").toString()
            view.findViewById<EditText>(R.id.edit_title_text).setText(title)
            view.findViewById<EditText>(R.id.edit_content_text).setText(content)
            //view.findViewById<TextView>(R.id.tv_book_purchase_date).text = bundle.getString("bookPurchaseDate")
        }

        view.findViewById<Button>(R.id.complete_button).setOnClickListener {
            // アイテムデータをホーム画面へ渡す処理
            setFragmentResult("itemEditedData", bundleOf(
                "itemTitle" to title,
                "itemContent" to content,
                //"bookPurchaseDate" to book.date
            ))

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