package com.example.MorozovHints.L101.presentation.mainActivity

import androidx.recyclerview.widget.DiffUtil
import com.example.MorozovHints.L101.domain.ShopItem

/**
 * Callback - сравнивает списки после удаления элемент и работает как грамотная замена
 * notifydatasetchange
 */
class X_ShopListDiffCallback (
    private val oldList: List<ShopItem>,
    private val newList: List<ShopItem>
) : DiffUtil.Callback()
{
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    //
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem==newItem
    }
}