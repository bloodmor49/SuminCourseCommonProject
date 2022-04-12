package com.example.MorozovHints.L110.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.MorozovHints.L110.domain.entity.Level
import com.example.MorozovHints.R
import com.example.MorozovHints.databinding.FragmentChooseLevelBinding

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
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentGameContainerView,GameFragment.newInstance(level))
            .addToBackStack(GameFragment.NAME)
            .commit()
    }

    companion object {

        const val NAME = "ChooseLevelFragment"
        fun newInstance(): ChooseLevelFragment {
            return ChooseLevelFragment()
        }
    }

}