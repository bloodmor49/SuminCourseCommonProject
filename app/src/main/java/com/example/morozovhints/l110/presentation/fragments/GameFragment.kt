package com.example.morozovhints.l110.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.morozovhints.l110.domain.entity.GameResult
import com.example.morozovhints.l110.presentation.gameviewmodel.GameViewModel
import com.example.morozovhints.l110.presentation.gameviewmodel.GameViewModelFactory
import com.example.morozovhints.databinding.FragmentGameBinding


class GameFragment : Fragment() {

//  получение аргументов safeArgs из nav - 1 способ - он мне больше нравится.
//  private val args by navArgs<GameFragmentArgs>()

    private val viewModelFactory by lazy {
        //получение аргументов safeArgs из nav - 2 способ
        val args = GameFragmentArgs.fromBundle(requireArguments())
        GameViewModelFactory(args.level, requireActivity().application)
    }
//    private lateinit var level: Level

    //обычный способ инициализации вью модел
    //private lateinit var gameviewmodel: GameViewModel

    //ленивая инициалиация. инициализация будет при первом обращении к данному объекту. не будет краша.
    //разницы нет, но так лучше.
    private val gameviewmodel: GameViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
    }

//    private val tvOptions by lazy {
//        mutableListOf<TextView>().apply {
//            add(viewBinding.tvOption1)
//            add(viewBinding.tvOption2)
//            add(viewBinding.tvOption3)
//            add(viewBinding.tvOption4)
//        }
//
//    }

    private var _viewBinding: FragmentGameBinding? = null
    private val viewBinding: FragmentGameBinding
        get() = _viewBinding ?: throw RuntimeException("FragmentGameBinding = null ")

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        parseArgs()
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _viewBinding = FragmentGameBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.gameviewmodel = gameviewmodel
        viewBinding.lifecycleOwner = viewLifecycleOwner

        //обычный способ присоединения viewmodel
//        gameviewmodel = ViewModelProvider(this,
//            getInstance(requireActivity().application))[GameViewModel::class.java]

        observeViewModel()
//        setClickListenersToOptions()

    }
//
//    private fun setClickListenersToOptions() {
//        for (tvOption in tvOptions) {
//            tvOption.setOnClickListener {
//                gameviewmodel.chooseAnswer(tvOption.text.toString().toInt())
//            }
//        }
//    }

    private fun observeViewModel() {

//        gameviewmodel.question.observe(viewLifecycleOwner) {
//            viewBinding.tvSum.text = it.sum.toString()
//            viewBinding.tvLeftNumber.text = it.visibleNumber.toString()
//            for (i in 0 until tvOptions.size) {
//                tvOptions[i].text = it.options[i].toString()
//            }
//        }

//        gameviewmodel.percentOfRightAnswers.observe(viewLifecycleOwner) {
//            viewBinding.progressBar.setProgress(it, true)
//        }
//
//        gameviewmodel.enoughCountOfRightAnswers.observe(viewLifecycleOwner) {
//            viewBinding.tvAnswersProgress.setTextColor(getColorByState(it))
//        }
//
//        gameviewmodel.enoughPercentOfRightAnswers.observe(viewLifecycleOwner) {
//            val color = getColorByState(it)
//            viewBinding.progressBar.progressTintList = ColorStateList.valueOf(color)
//        }

//        gameviewmodel.formatedTime.observe(viewLifecycleOwner) {
//            viewBinding.tvTimer.text = it
//        }

//        gameviewmodel.minPercent.observe(viewLifecycleOwner) {
//            viewBinding.progressBar.secondaryProgress = it
//        }

        gameviewmodel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }

//        gameviewmodel.progressAnswers.observe(viewLifecycleOwner) {
//            viewBinding.tvAnswersProgress.text = it
//        }
//    }

//    private fun getColorByState(goodState: Boolean): Int {
//        val colorResId = if (goodState) {
//            android.R.color.holo_green_light
//        } else {
//            android.R.color.holo_red_light
//        }
//        return ContextCompat.getColor(requireContext(), colorResId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    //проверка, что аргументы приняты и существуют. Переводим из байткода обратно к Level.
//    private fun parseArgs() {
//        requireArguments().getParcelable<Level>(KEY_VALUE)?.let {
//            level = it
//        }
//    }


    private fun launchGameFinishedFragment(gameResult: GameResult) {
        findNavController().navigate(GameFragmentDirections.actionGameFragment2ToGameFinishedFragment(
            gameResult))


//        requireActivity().supportFragmentManager.beginTransaction()
//            .replace(R.id.fragmentGameContainerView, GameFinishedFragment.newInstance(gameResult))
//            .addToBackStack(null)
//            .commit()

//        val args = Bundle().apply {
//            putParcelable(GameFinishedFragment.KEY_GAME_RESULT, gameResult)
//        }
    }

//    companion object {
//
//        const val NAME = "GameFragment"
//        private const val KEY_VALUE = "level"
//
//        fun newInstance(level: Level): GameFragment {
//            return GameFragment().apply {
//                arguments = Bundle().apply {
//                    putParcelable(KEY_VALUE, level)
//                }
//            }
//        }
//    }
}