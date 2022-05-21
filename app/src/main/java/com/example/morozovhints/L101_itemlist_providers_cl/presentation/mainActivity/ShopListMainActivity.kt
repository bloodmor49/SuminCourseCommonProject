package com.example.morozovhints.L101_itemlist_providers_cl.presentation.mainActivity

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.morozovhints.L101_itemlist_providers_cl.domain.entities.ShopItem
import com.example.morozovhints.L101_itemlist_providers_cl.presentation.ShopApplication
import com.example.morozovhints.L101_itemlist_providers_cl.presentation.ShopItemFragment
import com.example.morozovhints.L101_itemlist_providers_cl.presentation.ViewModelFactory
import com.example.morozovhints.L101_itemlist_providers_cl.presentation.shopItemActivity.ShopItemActivity
import com.example.morozovhints.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import javax.inject.Inject
import kotlin.concurrent.thread

class   ShopListMainActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var shopListAdapter: ShopListAdapter
    private var shopItemContainer: FragmentContainerView? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as ShopApplication).component
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop_list_main)
        shopItemContainer = findViewById(R.id.shop_item_container)
        setUpRecyclerView()
        setUpViewModel()
        val buttonAddItem = findViewById<FloatingActionButton>(R.id.floatingActionButtonAddShopItem)
        buttonAddItem.setOnClickListener {
            if (isOnePaneMode()) {
                val intent = ShopItemActivity.newIntentAddItem(this)
                startActivity(intent)
            } else {
                launchFragment(ShopItemFragment.newInstanceAddItem())
            }
        }

        // получаем список предметов через contentProvider.
        thread {
            val cursor = contentResolver.query(
                Uri.parse("content://com.example.morozovhints/shopItems"),
                null,
                null,
                null,
                null,
                null
            )

            while (cursor?.moveToNext() == true) {
                Log.i("myProvider", "Cursor: ${cursor.columnNames}")
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
                val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))
                val count = cursor.getInt(cursor.getColumnIndexOrThrow("count"))
                val enabled = cursor.getInt(cursor.getColumnIndexOrThrow("enabled")) > 0
                val shopItem = ShopItem(
                    id = id,
                    name = name,
                    count = count,
                    enabled = enabled
                )
                Log.i("myProvider", "Cursor: $shopItem")
            }
            cursor?.close()
        }
    }

    private fun setUpViewModel() {
        //Задаем viewModel
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        //обрабатываем LiveData
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

                //с помощью viewModel
//                viewModel.deleteShopItem(item)
                thread {
                    contentResolver?.delete(
                        Uri.parse("content://com.example.morozovhints/shopItems"),
                        null,
                        arrayOf(item.id.toString())
                    )
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleCallback)
        itemTouchHelper.attachToRecyclerView(recyclerViewShopItems)
    }

    private fun setUpClickListener() {
        shopListAdapter.onShopItemClickListener = {

            if (isOnePaneMode()) {
                val intent = ShopItemActivity.newIntentEditItem(this, it.id)
                startActivity(intent)
            } else {
                launchFragment(ShopItemFragment.newInstanceEditItem(it.id))
            }
        }
    }

    private fun setUpLongClickListener() {
        shopListAdapter.onShopItemLongClickListener = {
            viewModel.changeEnableState(it)
        }
    }

    private fun isOnePaneMode(): Boolean {
        return shopItemContainer == null
    }

    private fun launchFragment(fragment: Fragment) {
        //отрубить предыдущий фрагмент из стэка
        supportFragmentManager.popBackStack()
        //начать транзакцию с добавлением фрагмента
        supportFragmentManager.beginTransaction()
            //добавить фрагмент в контейнер с заменой предыдущего
            .replace(R.id.shop_item_container, fragment)
            //добавить фрагмент в стэк. Вместо нуля можно указать конкретный фрагмент, чтобы
            //сбрасывались все до него
            .addToBackStack(null)
            .commit()
    }

    fun onEditingFinish() {
        Toast.makeText(this, "Success", Toast.LENGTH_LONG).show()
        supportFragmentManager.popBackStack()
    }

    override fun onEditingFinished() {
        Toast.makeText(this@ShopListMainActivity, "Success", Toast.LENGTH_LONG).show()
        supportFragmentManager.popBackStack()
    }
}