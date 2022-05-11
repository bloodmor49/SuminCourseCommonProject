package com.example.morozovhints.l110_jetpack.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.morozovhints.databinding.FragmentGameFinishedBinding
import com.example.morozovhints.l110_jetpack.domain.entity.GameResult


class GameFinishedFragment : Fragment() {

    private val args by navArgs<GameFinishedFragmentArgs>()

    private lateinit var gameResult: GameResult

    private var _viewBinding: FragmentGameFinishedBinding? = null
    private val viewBinding: FragmentGameFinishedBinding
        get() = _viewBinding ?: throw RuntimeException("FragmentGameFinishedBinding = null ")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //parseArgs()
        gameResult = args.gameResult
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _viewBinding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickListeners()
        bindViews()
    }

    private fun setupClickListeners() {
//        val callback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                retryGame()
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        viewBinding.buttonRetry.setOnClickListener {
            retryGame()
        }
    }

    private fun bindViews() {
//через dataBinding логика установки значений ушла в BindingAdapter
        viewBinding.gameResult = args.gameResult

//     через ViewBinding
        //        with(viewBinding) {
//            emojiResult.setImageResource(getSmileResId())
//            tvRequiredAnswers.text = String.format(
//                getString(R.string.required_score),
//                gameResult.gameSettings.minCountOfRightAnswers
//            )
//            tvScoreAnswers.text = String.format(
//                getString(R.string.score_answers),
//                gameResult.countOfRightAnswers
//            )
//            tvRequiredPercentage.text = String.format(
//                getString(R.string.required_percentage),
//                gameResult.gameSettings.minPercentOfRightAnswers
//            )
//            tvScorePercentage.text = String.format(
//                getString(R.string.score_percentage),
//                getPercentOfRightAnswers()
//            )
//        }
    }
//
//    private fun getSmileResId(): Int {
//        return if (gameResult.winner) {
//            R.drawable.common_google_signin_btn_icon_light
//        } else {
//            R.drawable.common_google_signin_btn_icon_dark
//        }
//    }
//
//    private fun getPercentOfRightAnswers() = with(gameResult) {
//        if (countOfQuestions == 0) {
//            0
//        } else {
//            ((countOfRightAnswers / countOfQuestions.toDouble()) * 100).toInt()
//        }
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

//    private fun parseArgs() {
//        requireArguments().getParcelable<GameResult>(KEY_GAME_RESULT)?.let {
//            gameResult = it
//        }
//    }

    private fun retryGame() {
//        requireActivity().supportFragmentManager.popBackStack(GameFragment.NAME,
//            FragmentManager.POP_BACK_STACK_INCLUSIVE)

        findNavController().popBackStack()

    }


//    companion object {
//
//        private const val KEY_GAME_RESULT = "game_result"
//
//        fun newInstance(gameResult: GameResult): GameFinishedFragment {
//            return GameFinishedFragment().apply {
//                arguments = Bundle().apply {
//                    putParcelable(KEY_GAME_RESULT, gameResult)
//                }
//            }
//        }
//    }
}