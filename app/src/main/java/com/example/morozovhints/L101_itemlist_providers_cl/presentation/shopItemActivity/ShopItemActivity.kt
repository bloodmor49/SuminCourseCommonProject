package com.example.morozovhints.L101_itemlist_providers_cl.presentation.shopItemActivity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.morozovhints.L101_itemlist_providers_cl.domain.entities.ShopItem
import com.example.morozovhints.L101_itemlist_providers_cl.presentation.ShopItemFragment
import com.example.morozovhints.R

class ShopItemActivity : AppCompatActivity(), ShopItemFragment.OnEditingFinishListener {

    private var screenMode = MODE_UNKNOWN
    private var shopItemId = ShopItem.UNDEFINED_ID

    override fun onEditingFinished() {
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_shop_item)
        //проверяем, что пришли не нули.
        parseIntent()
        //нужно чтобы onCreate создавалась 1 раз.
        if (savedInstanceState==null) {
            launchRightMode()
        }
    }

    //Для работы с фрагментами используется Fragment Manager.
    //Мы вызываем метод фрагмента по его созданию и передаче данных.
    private fun launchRightMode() {
        //создаем фрагмент в зависимости от режима
        val fragment = when (screenMode) {
            MODE_EDIT -> ShopItemFragment.newInstanceEditItem(shopItemId)
            MODE_ADD -> ShopItemFragment.newInstanceAddItem()
            else -> throw RuntimeException("Unknown screen mode $screenMode")
        }
        // подключаем FragmentManager -> начинаем процесс добавления фрагмента 
        supportFragmentManager.beginTransaction()
            //добавление фрагмента в контейнер путем замены предыдущего
            .replace(R.id.shop_item_container,fragment)
            //запуск транзакции
            .commit()
    }

    //проверка на получение данных
    private fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD)
            throw RuntimeException("Unknown screen mode $mode")
        screenMode = mode
        if (screenMode == MODE_EDIT && !intent.hasExtra(EXTRA_SHOP_ITEM_ID))
            throw RuntimeException("Param id is absent")

        if (screenMode == MODE_EDIT) {
            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, -1)
        }
    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_UNKNOWN = ""

        fun newIntentAddItem(context: Context): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
            val intent = Intent(context, ShopItemActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            return intent
        }

    }


}