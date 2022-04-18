package com.example.morozovhints.L101_itemlist_pg.presentation.mainActivity

import androidx.recyclerview.widget.DiffUtil
import com.example.morozovhints.L101_itemlist_pg.domain.ShopItem

class ShopItemDiffCallback : DiffUtil.ItemCallback<ShopItem>() {

    override fun areItemsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShopItem, newItem: ShopItem): Boolean {
        return oldItem == newItem
    }
}