package com.example.morozovhints.l121_courutines_advanced

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.morozovhints.databinding.ActivityMainFactorialBinding

class MainActivityFactorial : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainFactorialBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observeViewModel()
        binding.buttonCalculate.setOnClickListener {
            viewModel.calculate(binding.editTextNumber.text.toString())
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(this) {
            binding.progressBarLoading.visibility = View.GONE
            binding.buttonCalculate.isEnabled = true
            when (it) {
                is Error -> Toast.makeText(this, "Ошибка", Toast.LENGTH_LONG).show()
                is Progress -> {
                    binding.progressBarLoading.visibility = View.VISIBLE
                    binding.buttonCalculate.isEnabled = false
                }
                is Result -> binding.textViewFactorial.text = it.factorial
            }
        }
    }
}