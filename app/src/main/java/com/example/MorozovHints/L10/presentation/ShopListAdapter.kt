package com.example.MorozovHints.L10.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.MorozovHints.L10.domain.ShopItem
import com.example.MorozovHints.R

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {

    var shopList = listOf<ShopItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

    class ShopItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewShopItemName = itemView.findViewById<TextView>(R.id.textViewShopItemName)
        val textViewShopItemCount = itemView.findViewById<TextView>(R.id.textViewShopItemCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        val layout =
            when (viewType) {
                VIEW_TYPE_ENABLED -> R.layout.shop_item_enabled
                VIEW_TYPE_DISABLED -> R.layout.shop_item_disabled
                else -> throw RuntimeException("Unknown viewType position: $viewType")
            }
        val view =
            LayoutInflater.from(parent.context).inflate(layout, parent, false)
        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val shopItem = shopList[position]
        with(holder) {
            itemView.setOnLongClickListener{
                onShopItemLongClickListener?.invoke(shopItem)
                true
            }
            itemView.setOnClickListener {
                onShopItemClickListener?.invoke(shopItem)
                true
            }
            textViewShopItemName.text = shopItem.name
            textViewShopItemCount.text = shopItem.count.toString()
        }
    }

    //Метод для определения какой макет используется для каждого элемента элемента.
    //В нашем случае активировано или не активировано.
    override fun getItemViewType(position: Int): Int {
        val item = shopList[position]
        return if (item.enabled) VIEW_TYPE_ENABLED
        else VIEW_TYPE_DISABLED
    }

    override fun getItemCount(): Int = shopList.size

    interface OnShopItemLongClickListener{
        fun onShopItemLongClick(shopItem: ShopItem)
    }

    interface OnShopItemClickListener{
        fun onShopItemClick(shopItem: ShopItem)
    }

    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0

        //скрытые экраны уходят в пул, размера пула
        const val MAX_POOL_SIZE = 15

    }
}