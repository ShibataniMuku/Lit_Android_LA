package com.shibatani.mukkun.product_memo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener

class ItemFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_item, container, false)
        activity?.title = "メモの詳細"

        // 一覧画面から渡されたデータをviewに表示する
        setFragmentResultListener("itemData") { _, bundle ->
            view.findViewById<TextView>(R.id.cell_item_title_text).text = bundle.getString("itemTitle")
            view.findViewById<TextView>(R.id.cell_item_content_text).text = bundle.getInt("itemContent").toString()
            //view.findViewById<TextView>(R.id.tv_book_purchase_date).text = bundle.getString("bookPurchaseDate")
        }

        return view
    }
}