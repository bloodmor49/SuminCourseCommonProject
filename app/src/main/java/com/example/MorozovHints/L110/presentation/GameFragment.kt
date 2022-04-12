package com.example.MorozovHints.L110.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.getInstance
import com.example.MorozovHints.L110.domain.entity.GameResult
import com.example.MorozovHints.L110.domain.entity.Level
import com.example.MorozovHints.R
import com.example.MorozovHints.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    private val viewModelFactory by lazy {
        GameViewModelFactory(level, requireActivity().application)
    }
    private lateinit var level: Level

    //обычный способ инициализации вью модел
    // private lateinit var viewModel: GameViewModel

    //ленивая инициалиация. инициализация будет при первом обращении к данному объекту. не будет краша.
    //разницы нет, но так лучше.
    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[GameViewModel::class.java]
    }

    private val tvOptions by lazy {
        mutableListOf<TextView>().apply {
            add(viewBinding.tvOption1)
            add(viewBinding.tvOption2)
            add(viewBinding.tvOption3)
            add(viewBinding.tvOption4)
        }

    }
    private var _viewBinding: FragmentGameBinding? = null
    private val viewBinding: FragmentGameBinding
        get() = _viewBinding ?: throw RuntimeException("FragmentGameBinding = null ")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _viewBinding = FragmentGameBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //обычный способ присоединения viewmodel
//        viewModel = ViewModelProvider(this,
//            getInstance(requireActivity().application))[GameViewModel::class.java]

        observeViewModel()
        setClickListenersToOptions()

    }

    private fun setClickListenersToOptions() {
        for (tvOption in tvOptions) {
            tvOption.setOnClickListener {
                viewModel.chooseAnswer(tvOption.text.toString().toInt())
            }
        }
    }

    private fun observeViewModel() {

        viewModel.question.observe(viewLifecycleOwner) {
            viewBinding.tvSum.text = it.sum.toString()
            viewBinding.tvLeftNumber.text = it.visibleNumber.toString()
            for (i in 0 until tvOptions.size) {
                tvOptions[i].text = it.options[i].toString()
            }
        }

        viewModel.percentOfRightAnswers.observe(viewLifecycleOwner) {
            viewBinding.progressBar.setProgress(it, true)
        }

        viewModel.enoughCountOfRightAnswers.observe(viewLifecycleOwner) {
            viewBinding.tvAnswersProgress.setTextColor(getColorByState(it))
        }

        viewModel.enoughPercentOfRightAnswers.observe(viewLifecycleOwner) {
            val color = getColorByState(it)
            viewBinding.progressBar.progressTintList = ColorStateList.valueOf(color)
        }

        viewModel.formatedTime.observe(viewLifecycleOwner) {
            viewBinding.tvTimer.text = it
        }

        viewModel.minPercent.observe(viewLifecycleOwner) {
            viewBinding.progressBar.secondaryProgress = it
        }

        viewModel.gameResult.observe(viewLifecycleOwner) {
            launchGameFinishedFragment(it)
        }

        viewModel.progressAnswers.observe(viewLifecycleOwner) {
            viewBinding.tvAnswersProgress.text = it
        }
    }

    private fun getColorByState(goodState: Boolean): Int {
        val colorResId = if (goodState) {
            android.R.color.holo_green_light
        } else {
            android.R.color.holo_red_light
        }
        return ContextCompat.getColor(requireContext(), colorResId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    //проверка, что аргументы приняты и существуют. Переводим из байткода обратно к Level.
    private fun parseArgs() {
        requireArguments().getParcelable<Level>(KEY_VALUE)?.let {
            level = it
        }
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentGameContainerView, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    companion object {

        const val NAME = "GameFragment"
        private const val KEY_VALUE = "level"

        fun newInstance(level: Level): GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_VALUE, level)
                }
            }
        }
    }
}