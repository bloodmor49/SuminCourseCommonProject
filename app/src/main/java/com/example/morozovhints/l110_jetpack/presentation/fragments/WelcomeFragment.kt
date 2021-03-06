package com.example.morozovhints.l110_jetpack.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.morozovhints.R
import com.example.morozovhints.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    //Новая фича - viewBinding. BuildGradle -> ViewBindning = true
    //Это аналог findviewbyid, но он сразу определяет все вью - не нужно вручную всё искать.
    //здесь инфиксная нотация используется, если кто то попробует вызвать что - то нулевое.
    //берем _vb = нуль
    private var _viewBinding: FragmentWelcomeBinding? = null

    //потом переопределяем vb с геттером от _vb или бросаем ошибку. Теперь не нужно всё на null чек.
    private val viewBinding: FragmentWelcomeBinding
        get() = _viewBinding ?: throw RuntimeException("_ViewBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        //привязываем детали экрана с XML через классический инфлейтер
        _viewBinding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //теперь можем возвращаться ко всем элементам изи с помощью биндинга
        //в данном случае он точно не нулевой, так как иначе будет ошибка при геттере.
        viewBinding.buttonAcceptRules.setOnClickListener {
            launchChooseLevelFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //если обратимся к переменной до создания или после уничтожения View, то будет ошибка.
        _viewBinding = null
    }

    /**
     * Запуск выбора уровня.
     * Фактически замена фрагмента в фрагмент контейнере.
     */
    private fun launchChooseLevelFragment() {
//        requireActivity().supportFragmentManager.beginTransaction()
//            .replace(R.id.fragmentGameContainerView,ChooseLevelFragment.newInstance())
//            .addToBackStack(NAME)
//            .commit()
        findNavController().navigate(R.id.action_welcomeFragment_to_chooseLevelFragment22)
    }
}