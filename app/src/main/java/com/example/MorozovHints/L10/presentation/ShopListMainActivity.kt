package com.example.MorozovHints.L10.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.MorozovHints.L10.domain.ShopItem
import com.example.MorozovHints.R
import java.util.*

class ShopListMainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_list_main)
        setUpRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.shopList.observe(this) {
            shopListAdapter.submitList(it)
        }
    }

    private fun setUpRecyclerView() {
        val recyclerViewShopItems = findViewById<RecyclerView>(R.id.recyclerViewShopItems)
        shopListAdapter = ShopListAdapter()
        with(recyclerViewShopItems) {
            adapter = shopListAdapter

            //Пулы для хранения скрытых viewholder.
            recycledViewPool.setMaxRecycledViews(ShopListAdapter.VIEW_TYPE_ENABLED,
                ShopListAdapter.MAX_POOL_SIZE)

            recycledViewPool.setMaxRecycledViews(ShopListAdapter.VIEW_TYPE_DISABLED,
                ShopListAdapter.MAX_POOL_SIZE)
        }

        setUpClickListener()
        setUpLongClickListener()
        setUpOnSwipeListener(recyclerViewShopItems)
    }

    private fun setUpOnSwipeListener(recyclerViewShopItems: RecyclerView?) {
        val simpleCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = shopListAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteShopItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(recyclerViewShopItems)
    }
    private fun setUpClickListener() {
        shopListAdapter.onShopItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }
    private fun setUpLongClickListener() {
        shopListAdapter.onShopItemClickListener = {
            Log.d("shoplist", "Нажатие на ${it.name}")
        }
    }
}