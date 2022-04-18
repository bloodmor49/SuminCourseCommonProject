package com.example.morozovhints.L101_itemlist_pg.presentation

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.morozovhints.L101_itemlist_pg.domain.ShopItem
import com.example.morozovhints.L101_itemlist_pg.presentation.mainActivity.ShopListMainActivity
import com.example.morozovhints.L101_itemlist_pg.presentation.shopItemActivity.ShopItemViewModel
import com.example.morozovhints.R
import com.google.android.material.textfield.TextInputLayout

/**
 * Класс фрагмента. Фрагмент является самостоятельным элементов.
 * Фрагмент привязан к определенной активности.
 * Фрагмент - это не активность, поэтому у неё нет намерений Intent.
 * Фрагмент не может требовать, чтобы активити была запущена каким - либо определенным образом.
 *
 * НИКОГДА НЕЛЬЗЯ ПЕРЕДАВАТЬ ПАРАМЕТРЫ В КОНСТРУКТОР ФРАГМЕНТА, так как при повороте (например)
 * экрана создается экземпляр ПУСТОГО (c нулевыми пар-ми) конструктора.
 * Так оно работает только при первом создании фрагмента.
 * Поэтому нужно использовать аналог intent - arguments т.е.
 */
class ShopItemFragment() : Fragment() {

    private lateinit var viewModel: ShopItemViewModel
    private var onEditingFinishListener: OnEditingFinishListener? = null

    private lateinit var tilName: TextInputLayout
    private lateinit var tilCount: TextInputLayout
    private lateinit var etName: EditText
    private lateinit var etCount: EditText
    private lateinit var buttonSave: Button

    private var screenMode: String = MODE_UNKNOWN
    private var shopItemId: Int = ShopItem.UNDEFINED_ID

    override fun onAttach(context: Context){
        super.onAttach(context)
        if (context is OnEditingFinishListener) {
            onEditingFinishListener = context
        } else {
            throw RuntimeException("Activity must implement OnEditingFinishListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    //Функция нужна для того, чтобы создать из XML макета видимость на экране VIEW.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_shop_item, container, false)
    }

    //Функция вызывается, когда VIEW уже 100% создана.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[ShopItemViewModel::class.java]
        initViews(view)
        addTextChangeListeners()
        launchRightMode()
        observeViewModel()
    }

    // СОБЕСЕДОВАНИЕ - ВАЖНО.
    // observe(viewLifecycleOwner) т.к. фрагмент и активность обладают разными жизн. циклами,
    // фрагмент может жить ДОЛЬШЕ, чем активити, поэтому вместо observe(this) ставим в обзервер
    // ВЛАДЕЛЬЦА ФРАГМЕНТА!!!!!!! (viewLifeCycleOwner). (This - это фрагмент)
    private fun observeViewModel() {
        viewModel.errorInputCount.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.ErrorInput)
            } else {
                null
            }
            tilName.error = message
        }

        viewModel.errorInputCount.observe(viewLifecycleOwner) {
            val message = if (it) {
                getString(R.string.ErrorInput)
            } else {
                null
            }
            tilCount.error = message
        }
        //у фрагментов нет метода finish() для закрытия
        //поэтому берем метод onBackPressed() - аналог кнопки возврата.
        //можно использовать requireActivity() (отличие от activity в том, что req не nullable)
        //Если активити не равна null, то можно брать requireActivity. Иначе лучше activity с пров-й.
        //Таких методов полно, к контексту тоже относятся.
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            //finish()
            //activity?.onBackPressed()
            //requireActivity().onBackPressed()
            (activity as ShopListMainActivity).onEditingFinish()
        }
    }

    //запуск корректного режима фрагмента
    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    //запуск корректного режима фрагмента, используется метод работы с TextInputLayout.
    private fun addTextChangeListeners() {
        tilName.addOnEditTextAttachedListener {
            object : TextWatcher {
                //до изм. текста
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {

                }

                //во время изм. текста
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.resetErrorInputName()
                }

                //после изм. текста
                override fun afterTextChanged(s: Editable?) {
                }
            }
        }
        tilCount.addOnEditTextAttachedListener {
            object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int,
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel.resetErrorInputCount()
                }

                override fun afterTextChanged(s: Editable?) {

                }
            }
        }
    }

    //кнопка запуска режима добавления shopItem.
    private fun launchAddMode() {
        //никаких наблюдателей. тупо новый предмет и кнопка его добавляет в коллекцию.
        buttonSave.setOnClickListener {
            viewModel.addShopItem(etName.text.toString(), etCount.text.toString())
        }
    }

    //пуск редактирования режима.
    private fun launchEditMode() {
        //вызываем метод взять элемент из вью модели, которая через домен берет метод в дата
        viewModel.getShopItem(shopItemId)
        //далее наблюдаем за этим shopItem и его изменениями, где ставим текст какой нам нужен.
        viewModel.shopItem.observe(viewLifecycleOwner) {
            etName.setText(it.name)
            etCount.setText(it.count.toString())
        }
        //а кнопку будет редактировать все.
        buttonSave.setOnClickListener {

            viewModel.editShopItem(etName.text?.toString(), etCount.text?.toString().toString())
        }
    }

    //ввод параметров и проверка на правильность ввода.
    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = args.getString(SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT && !args.containsKey(SHOP_ITEM_ID)) {
            throw RuntimeException("Param shop item id is absent")
        }
        if (screenMode == MODE_EDIT) {
            shopItemId = args.getInt(SHOP_ITEM_ID, -1)
        }
    }

    //Во фрагменте нет findViewById. Но они есть во view.
    private fun initViews(view: View) {
        tilName = view.findViewById(R.id.tilName)
        tilCount = view.findViewById(R.id.tilCount)
        etName = view.findViewById(R.id.TextInputEditTextName)
        etCount = view.findViewById(R.id.TextInputEditTextCount)
        buttonSave = view.findViewById(R.id.buttonToSave)
    }

    interface OnEditingFinishListener{
        fun onEditingFinished()
    }

    //Содержит все константы фрагмента,
    //А также методы запуска.
    companion object {
        private const val SCREEN_MODE = "extra_mode"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val SHOP_ITEM_ID = "extra_shop_item_id"
        private const val MODE_UNKNOWN = ""

        //выбор режима работы фрагмента (добавление нового предмета или редактирование существ.-го)
        fun newInstanceAddItem(): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(shopItemId: Int): ShopItemFragment {
            return ShopItemFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(SHOP_ITEM_ID, shopItemId)
                }
            }
        }
    }
}