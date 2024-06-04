package com.oneclickdrive.practicaldemo.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.oneclickdrive.practicaldemo.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = this.application.let { HomeViewModelFactory(it) }
        viewModel = ViewModelProviders.of(this, factory)[HomeViewModel::class.java]

        viewModel.result.observe(this, Observer { result ->
            binding.txtResult.text = result
        })

        binding.btnCalculate.setOnClickListener {
            if (!binding.edtNumber1.text.toString()
                    .isNullOrEmpty() && !binding.edtNumber2.text.toString()
                    .isNullOrEmpty() && !binding.edtNumber3.text.toString().isNullOrEmpty()
            ) {
                val list1 = binding.edtNumber1.text.toString()
                val list2 = binding.edtNumber2.text.toString()
                val list3 = binding.edtNumber3.text.toString()
                viewModel.calculate(list1, list2, list3)
            } else {
                Toast.makeText(this@HomeActivity, "Please enter all numbers", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }
}
