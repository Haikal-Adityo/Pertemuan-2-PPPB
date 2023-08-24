package com.example.pertemuan1

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pertemuan1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
    }

    private fun setupViews() {
        binding.txtNumber.text = number.toString()

        binding.btnToast.setOnClickListener {
            Toast.makeText(this@MainActivity, "BMI $number", Toast.LENGTH_SHORT).show()
        }

        binding.btnHitung.setOnClickListener {
            calculateAndDisplayBMI()
        }
    }

    private fun calculateAndDisplayBMI() {
        val berat = binding.BeratBadan.text.toString().toFloat()
        val tinggi = binding.TinggiBadan.text.toString().toFloat() / 100
        val bmi = berat / (tinggi * tinggi)

        val roundedBmi = String.format("%.1f", bmi)

        binding.txtNumber.text = roundedBmi
    }
}
