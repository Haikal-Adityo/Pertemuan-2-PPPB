package com.example.pertemuan1

import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.pertemuan1.databinding.ActivityMainBinding
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var number: Float = 0.0F

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
            val berat = binding.BeratBadan.text.toString()
            val tinggi = binding.TinggiBadan.text.toString()

            if (berat.isNotEmpty() && tinggi.isNotEmpty()) {
                hitungBMI()
            } else {
                Toast.makeText(this@MainActivity, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }
        }

        // Mengunci key Enter
        binding.BeratBadan.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                return@setOnKeyListener true
            }
            false
        }

        binding.TinggiBadan.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                return@setOnKeyListener true
            }
            false
        }
    }

    private fun hitungBMI() {
        val berat = binding.BeratBadan.text.toString().toFloat()
        val tinggi = binding.TinggiBadan.text.toString().toFloat() / 100
        val bmi = berat / (tinggi * tinggi)

        val roundedBmi = String.format("%.1f", bmi)

        number = roundedBmi.toFloat()
        binding.txtNumber.text = roundedBmi

        // Mengubah warna text sesuai nilai BMI
        when {
            bmi < 17 -> binding.txtNumber.setTextColor(ContextCompat.getColor(this, R.color.colorSevere))
            bmi >= 17 && bmi < 18.5 -> binding.txtNumber.setTextColor(ContextCompat.getColor(this, R.color.colorLow))
            bmi >= 18.5 && bmi < 25 -> binding.txtNumber.setTextColor(ContextCompat.getColor(this, R.color.colorNormal))
            bmi >= 25 && bmi < 30 -> binding.txtNumber.setTextColor(ContextCompat.getColor(this, R.color.colorLow))
            else -> binding.txtNumber.setTextColor(ContextCompat.getColor(this, R.color.colorSevere))
        }
    }

}
