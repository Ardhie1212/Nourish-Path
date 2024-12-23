package com.example.nourishpath.ui.childinput

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.nourishpath.databinding.ActivityChildInputBinding
import com.example.nourishpath.ui.nutrient.NutrientActivity

class ChildInputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChildInputBinding
    private lateinit var viewModel: ChildInputViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChildInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[ChildInputViewModel::class.java]

        binding.btnNext.setOnClickListener {
            val usiaText = binding.etChildAge.text.toString()
            val beratBadanText = binding.etChildWeight.text.toString()
            val tinggiBadanText = binding.etChildHeight.text.toString()

            if (usiaText.isEmpty() || beratBadanText.isEmpty() || tinggiBadanText.isEmpty()) {
                Toast.makeText(this, "Make sure all data is entered", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val usia = usiaText.toIntOrNull() ?: 0
            val beratBadan = beratBadanText.toFloatOrNull() ?: 0f
            val tinggiBadan = tinggiBadanText.toFloatOrNull() ?: 0f

            viewModel.checkStuntingAndSaveData(usia, tinggiBadan, beratBadan)

            val intent = Intent(this, NutrientActivity::class.java)
            intent.putExtra("usia", usia)
            startActivity(intent)
            finish()
        }

        viewModel.stuntingStatus.observe(this) { status ->
            Toast.makeText(this, "Your child is $status", Toast.LENGTH_LONG).show()
        }
        viewModel.isLoading.observe(this) { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
        viewModel.errorMessage.observe(this) { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}