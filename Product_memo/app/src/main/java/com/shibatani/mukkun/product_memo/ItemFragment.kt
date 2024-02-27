package com.shibatani.mukkun.product_memo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener

class ItemFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_item, container, false)
        activity?.title = "書籍情報"

        // 一覧画面から渡されたデータをviewに表示する
        setFragmentResultListener("bookData") { _, bundle ->
            view.findViewById<TextView>(R.id.tv_book_name).text = bundle.getString("bookName")
            view.findViewById<TextView>(R.id.tv_book_price).text = bundle.getInt("bookPrice").toString()
            view.findViewById<TextView>(R.id.tv_book_purchase_date).text = bundle.getString("bookPurchaseDate")
        }

        return view
    }
}