package com.example.morozovhints.l110_jetpack.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.morozovhints.l110_jetpack.domain.entity.Level
import com.example.morozovhints.databinding.FragmentChooseLevelBinding

class ChooseLevelFragment : Fragment() {

    private var _viewBinding : FragmentChooseLevelBinding? = null
    private val viewBinding : FragmentChooseLevelBinding
    get() = _viewBinding ?: throw RuntimeException("FragmentChooseLevelBinding = null ")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _viewBinding = FragmentChooseLevelBinding.inflate(inflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding){
            buttonEasyLevel.setOnClickListener {
                launchGameFragment(Level.EASY)
            }
            buttonNormalLevel.setOnClickListener {
                launchGameFragment(Level.NORMAL)
            }
            buttonHardLevel.setOnClickListener {
                launchGameFragment(Level.HARD)
            }
            buttonTestLevel.setOnClickListener {
                launchGameFragment(Level.TEST)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }

    private fun launchGameFragment(level:Level){
//        requireActivity().supportFragmentManager.beginTransaction()
//            .replace(R.id.fragmentGameContainerView,GameFragment.newInstance(level))
//            .addToBackStack(GameFragment.NAME)
//            .commit()

//        val args = Bundle().apply {
//                putParcelable(GameFragment.KEY_VALUE, level)
//        }

        findNavController().navigate(
            ChooseLevelFragmentDirections.actionChooseLevelFragment2ToGameFragment2(level))
    }

//    companion object {
//
//        const val NAME = "ChooseLevelFragment"
//        fun newInstance(): ChooseLevelFragment {
//            return ChooseLevelFragment()
//        }
//    }

}