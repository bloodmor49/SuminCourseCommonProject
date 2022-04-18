//
//СТАРАЯ ВЕРСИЯ АКТВИТИ. ИСПОЛЬЗУЕТСЯ ДЛЯ СРАВНЕНИЯ С ФРАГМЕНТОМ ФУНКЦИЙ.
//
 package com.example.morozovhints.L101_itemlist_pg.presentation.shopItemActivity
//
//import android.content.Context
//import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.text.Editable
//import android.text.TextWatcher
//import android.util.Log
//import android.widget.Button
//import android.widget.EditText
//import androidx.lifecycle.ViewModelProvider
//import com.example.MorozovHints.L10.domain.ShopItem
//import com.example.MorozovHints.L10.presentation.mainActivity.MainViewModel
//import com.example.MorozovHints.L10.presentation.mainActivity.ShopListAdapter
//import com.example.MorozovHints.R
//import com.google.android.material.textfield.TextInputLayout
//
class XShopItemActivity : AppCompatActivity() {
//
//    private lateinit var viewModel: ShopItemViewModel
//
//    private lateinit var tilName: TextInputLayout
//    private lateinit var tilCount: TextInputLayout
//    private lateinit var etName: EditText
//    private lateinit var etCount: EditText
//    private lateinit var buttonSave: Button
//
//    private var screenMode = MODE_UNKNOWN
//    private var shopItemId = ShopItem.UNDEFINED_ID
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_edit_shop_item)
//        parseIntent()
//        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
//        initViews()
//        addTextChangeListeners()
//        launchRightMode()
//        observeViewModel()
//
//    }
//
//    private fun observeViewModel() {
//        viewModel.errorInputCount.observe(this) {
//            val message = if (it) {
//                getString(R.string.ErrorInput)
//            } else {
//                null
//            }
//            tilName.error = message
//        }
//
//        viewModel.errorInputCount.observe(this) {
//            val message = if (it) {
//                getString(R.string.ErrorInput)
//            } else {
//                null
//            }
//            tilCount.error = message
//        }
//        viewModel.shouldCloseScreen.observe(this) {
//            finish()
//        }
//    }
//
//    private fun launchRightMode() {
//        when (screenMode) {
//            MODE_EDIT -> launchEditMode()
//            MODE_ADD -> launchAddMode()
//        }
//    }
//
//    private fun addTextChangeListeners() {
//        tilName.addOnEditTextAttachedListener {
//            object : TextWatcher {
//                override fun beforeTextChanged(
//                    s: CharSequence?,
//                    start: Int,
//                    count: Int,
//                    after: Int,
//                ) {
//                }
//
//                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                    viewModel.resetErrorInputName()
//                }
//
//                override fun afterTextChanged(s: Editable?) {
//                }
//            }
//        }
//        tilCount.addOnEditTextAttachedListener {
//            object : TextWatcher {
//                override fun beforeTextChanged(
//                    s: CharSequence?,
//                    start: Int,
//                    count: Int,
//                    after: Int,
//                ) {
//                }
//
//                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                    viewModel.resetErrorInputCount()
//                }
//
//                override fun afterTextChanged(s: Editable?) {
//                }
//            }
//        }
//    }
//
//    private fun launchAddMode() {
//        buttonSave.setOnClickListener {
//            viewModel.addShopItem(etName.text.toString(), etCount.text.toString())
//        }
//    }
//
//    private fun launchEditMode() {
//        viewModel.getShopItem(shopItemId)
//        viewModel.shopItem.observe(this) {
//            etName.setText(it.name)
//            etCount.setText(it.count.toString())
//        }
//        buttonSave.setOnClickListener {

//            viewModel.editShopItem(etName.text?.toString(), etCount.text?.toString().toString())
//        }
//    }
//
//    private fun parseIntent() {
//        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
//            throw RuntimeException("Param screen mode is absent")
//        }
//        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
//        if (mode != MODE_EDIT && mode != MODE_ADD)
//            throw RuntimeException("Unknown screen mode $mode")
//        screenMode = mode
//        if (screenMode == MODE_EDIT && !intent.hasExtra(EXTRA_SHOP_ITEM_ID))
//            throw RuntimeException("Param id is absent")
//
//        if (screenMode == MODE_EDIT) {
//            shopItemId = intent.getIntExtra(EXTRA_SHOP_ITEM_ID, -1)
//        }
//    }
//
//    private fun initViews() {
//        tilName = findViewById(R.id.tilName)
//        tilCount = findViewById(R.id.tilCount)
//        etName = findViewById(R.id.TextInputEditTextName)
//        etCount = findViewById(R.id.TextInputEditTextCount)
//        buttonSave = findViewById(R.id.buttonToSave)
//    }
//
//    companion object {
//        private const val EXTRA_SCREEN_MODE = "extra_mode"
//        private const val MODE_EDIT = "mode_edit"
//        private const val MODE_ADD = "mode_add"
//        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"
//        private const val MODE_UNKNOWN = ""
//
//        fun newIntentAddItem(context: Context): Intent {
//            val intent = Intent(context, XShopItemActivity::class.java)
//            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
//            return intent
//        }
//
//        fun newIntentEditItem(context: Context, shopItemId: Int): Intent {
//            val intent = Intent(context, XShopItemActivity::class.java)
//            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
//            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
//            return intent
//        }
//
//    }
}