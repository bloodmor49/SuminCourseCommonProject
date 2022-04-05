package com.example.MorozovHints.L10.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.MorozovHints.R

class ShopItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textViewShopItemName = itemView.findViewById<TextView>(R.id.textViewShopItemName)
    val textViewShopItemCount = itemView.findViewById<TextView>(R.id.textViewShopItemCount)
}