package com.example.morozovhints.L101_itemlist_providers_cl.presentation.mainActivity

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.morozovhints.L101_itemlist_providers_cl.domain.entities.ShopItem
import com.example.morozovhints.R

class ShopListAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(ShopItemDiffCallback()) {

    /*var shopList = listOf<ShopItem>()
      //  set(value) {

            /*
            проблема NDSC в том, что при изменении списка весь bind переопределяется и
            увеличивается пул viewholder-ов. Для решени проблемы используется [ShopListDiffCallback]

            notifyDataSetChanged()
            */

            /* 2 вариант - ShopListDiffCallback. Минут в том, что пул все же обновляется.
            val callback = ShopListDiffCallback(shopList, value)
            val diffresult = DiffUtil.calculateDiff(callback)
            diffresult.dispatchUpdatesTo(this)
            */

       //     field = value
        }
        */

    var onShopItemLongClickListener: ((ShopItem) -> Unit)? = null
    var onShopItemClickListener: ((ShopItem) -> Unit)? = null

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
        val shopItem = getItem(position)
        with(holder) {
            itemView.setOnLongClickListener {
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
        val item = getItem(position)
        return if (item.enabled) VIEW_TYPE_ENABLED
        else VIEW_TYPE_DISABLED
    }

    //не нужно для ShopItemDiffCallback
    //override fun getItemCount(): Int = .size

    companion object {
        const val VIEW_TYPE_ENABLED = 1
        const val VIEW_TYPE_DISABLED = 0

        //скрытые экраны уходят в пул, размера пула
        const val MAX_POOL_SIZE = 15
    }
}