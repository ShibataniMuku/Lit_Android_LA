package com.shibatani.mukkun.product_memo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult

class ItemAddFragment : Fragment(){
    var title: String = ""
    var content: String = ""
    var index: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_item_add, container, false)

        activity?.title = "メモの編集"

//        // ホーム画面から渡されたデータをviewに表示する
//        setFragmentResultListener("itemEditedData") { _, bundle ->
//            title = bundle.getString("itemTitle").toString()
//            content = bundle.getString("itemContent").toString()
//            index = bundle.getInt("itemIndex")
//            view.findViewById<EditText>(R.id.edit_title_text).setText(title)
//            view.findViewById<EditText>(R.id.edit_content_text).setText(content)
//        }

        view.findViewById<Button>(R.id.complete_button).setOnClickListener {
            title = view.findViewById<EditText>(R.id.add_title_text).text.toString()
            content = view.findViewById<EditText>(R.id.add_content_text).text.toString()

            // アイテムデータをホーム画面へ渡す処理
            setFragmentResult("itemAddedData", bundleOf(
                "itemTitle" to title,
                "itemContent" to content,
                "itemIndex" to index
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