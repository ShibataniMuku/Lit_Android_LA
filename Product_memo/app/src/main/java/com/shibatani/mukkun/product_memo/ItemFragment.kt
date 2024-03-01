package com.shibatani.mukkun.product_memo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.shibatani.mukkun.product_memo.databinding.FragmentItemEditBinding

class ItemFragment : Fragment() {
    lateinit var title: String
    lateinit var content:String
    var index: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.fragment_item, container, false)

        activity?.title = "メモの詳細"

        // 一覧画面から渡されたデータをviewに表示する
        setFragmentResultListener("itemData") { _, bundle ->
            title = bundle.getString("itemTitle").toString()
            content = bundle.getString("itemContent").toString()
            index = bundle.getInt("itemIndex")
            view.findViewById<TextView>(R.id.cell_item_title_text).text = title
            view.findViewById<TextView>(R.id.cell_item_content_text).text = content
        }

        view.findViewById<Button>(R.id.edit_button).setOnClickListener {
            // アイテムデータを編集画面へ渡す処理
            setFragmentResult("itemEditedData", bundleOf(
                "itemTitle" to title,
                "itemContent" to content,
                "itemIndex" to index
            ))

            // 編集画面への遷移処理
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fl_activity_main, ItemEditFragment())
                .addToBackStack(null)
                .commit()
        }

        return view
    }
}