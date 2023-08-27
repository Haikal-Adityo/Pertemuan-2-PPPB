package com.example.pertemuan1

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.pertemuan1.databinding.ActivityMainBinding
import kotlin.math.ceil

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
            showToast("BMI $number")
        }

        binding.btnHitung.setOnClickListener {
            val berat = binding.txtBeratBadan.text.toString()
            val tinggi = binding.txtTinggiBadan.text.toString()

            if (berat.isNotEmpty() && tinggi.isNotEmpty()) {
                hitungBMI()
            } else {
                showToast("Please fill in all fields.")
            }
        }

        // Mengunci key Enter
        binding.txtBeratBadan.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                return@setOnKeyListener true
            }
            false
        }

        binding.txtTinggiBadan.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                return@setOnKeyListener true
            }
            false
        }

        // Membuat hanya bisa input angka
        binding.txtBeratBadan.inputType = EditorInfo.TYPE_CLASS_NUMBER
        binding.txtTinggiBadan.inputType = EditorInfo.TYPE_CLASS_NUMBER

        binding.txtTinggiBadan.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                return@setOnKeyListener true
            }
            false
        }
    }

    private fun hitungBMI() {
        val berat = binding.txtBeratBadan.text.toString().toFloat()
        val tinggi = binding.txtTinggiBadan.text.toString().toFloat() / 100

        if (berat <= 0 || tinggi <= 0) {
            showToast("Please enter valid values.")
            return
        }

        val bmi = (berat / (tinggi * tinggi)).toInt()

        number = bmi
        binding.txtNumber.text = number.toString()

        // Mengubah warna text dan menampilkan message sesuai nilai BMI
        when {
            bmi < 1 -> {
                binding.txtNumber.setTextColor(ContextCompat.getColor(this, R.color.black))
                showToast("Please enter valid values.")
            }
            bmi < 17 -> {
                binding.txtNumber.setTextColor(ContextCompat.getColor(this, R.color.colorSevere))
                showToast("Low BMI")
            }
            bmi >= 17 && bmi < 18.5 -> {
                binding.txtNumber.setTextColor(ContextCompat.getColor(this, R.color.colorLow))
                showToast("Moderate BMI")
            }
            bmi >= 18.5 && bmi < 25 -> {
                binding.txtNumber.setTextColor(ContextCompat.getColor(this, R.color.colorNormal))
                showToast("Normal BMI")
            }
            bmi in 25..29 -> {
                binding.txtNumber.setTextColor(ContextCompat.getColor(this, R.color.colorLow))
                showToast("Slightly high BMI")
            }
            else -> {
                binding.txtNumber.setTextColor(ContextCompat.getColor(this, R.color.colorSevere))
                showToast("Very high BMI")
            }
        }
    }


    private fun showToast(message: String) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
    }

}

