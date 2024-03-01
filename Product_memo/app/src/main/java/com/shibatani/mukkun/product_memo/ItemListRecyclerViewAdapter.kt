package com.shibatani.mukkun.product_memo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// コンストラクタにBookクラスを持つMutableListをセット
class ItemListRecyclerViewAdapter (
    private val bookListData: MutableList<Item>)
    : RecyclerView.Adapter<ItemListRecyclerViewAdapter.BookListRecyclerViewHolder>() {

    // リスナを格納する変数を定義（lateinitで初期化を遅らせている）
    private lateinit var listener: OnBookCellClickListener

    // インターフェースを作成
    interface  OnBookCellClickListener {
        fun onItemClick(book: Item)
    }

    // リスナーをセット
    fun setOnBookCellClickListener(listener: OnBookCellClickListener) {
        // 定義した変数listenerに実行したい処理を引数で渡す（BookListFragmentで渡している）
        this.listener = listener
    }

    // 画面部品要素を構成するクラスを定義
    class BookListRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // ここではcell_item_list.xmlより各レイアウト要素を取得して変数に格納している
        var itemTitle: TextView = itemView.findViewById(R.id.cell_item_title_text)
        var itemContent: TextView = itemView.findViewById(R.id.cell_item_content_text)
        var itemIndex: Int = 0
        //var bookPurchaseDate: TextView = itemView.findViewById(R.id.tv_book_purchase_date)
    }

    // 画面部品を保持する自作クラスであるBookListRecyclerViewHolderのオブジェクトを生成するメソッド
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : BookListRecyclerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cell_item_list, parent, false)
        return BookListRecyclerViewHolder(view)
    }

    // BookListRecyclerViewHolder内の各画面部品に表示したいデータを割り当てるメソッド
    override fun onBindViewHolder(holder: BookListRecyclerViewHolder, position: Int) {
        // positionは表示するリストbookListDataのインデックス番号のようなもの
        val book = bookListData[position]
        book.index = position

        // BookListRecyclerViewHolderより取得したレイアウト要素に書籍情報を格納
        holder.itemTitle.text = book.title
        holder.itemContent.text = book.content
        holder.itemIndex = book.index

        // 4. セルのクリックイベントにリスナをセット
        holder.itemView.setOnClickListener {
            // セルがクリックされた時にインターフェースの処理が実行される
            listener.onItemClick(book)
        }
    }

    // データ件数を返すメソッド
    override fun getItemCount() : Int = bookListData.size
}
