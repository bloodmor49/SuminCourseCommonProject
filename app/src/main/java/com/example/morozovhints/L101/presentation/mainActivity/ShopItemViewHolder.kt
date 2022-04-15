package com.example.morozovhints.L101.presentation.mainActivity

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.morozovhints.R

class ShopItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewShopItemName = itemView.findViewById<TextView>(R.id.textViewShopItemName)
    val textViewShopItemCount = itemView.findViewById<TextView>(R.id.textViewShopItemCount)
}