package com.zxdeveloper.bmicalculator

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightEditText: EditText = findViewById(R.id.weightEditText)
        val heightEditText: EditText = findViewById(R.id.heightEditText)
        val genderEditText: EditText = findViewById(R.id.genderEditText)
        val calculateButton: Button = findViewById(R.id.calculateButton)
        val resultTextView: TextView = findViewById(R.id.resultTextView)

        calculateButton.setOnClickListener {
            val weight: Double = weightEditText.text.toString().toDouble()
            val height: Double = heightEditText.text.toString().toDouble()
            val gender: String = genderEditText.text.toString()

            val person = Person(weight, height, gender)

            val bmi = person.calculateBMI()
            val result = person.getBMICategory(bmi)

            resultTextView.text = "Your BMI is: $bmi\n$result"
            resultTextView.setTextColor(
                when (result) {
                    BMICategory.NORMAL -> Color.GREEN
                    else -> Color.RED
                }
            )
        }
    }
}

class Person(private val weight: Double, private val height: Double, private val gender: String) {

    fun calculateBMI(): Double {
        return weight / (height * height)
    }

    fun getBMICategory(bmi: Double): BMICategory {
        return when {
            bmi < 18.5 -> BMICategory.UNDERWEIGHT
            bmi < 24.9 -> BMICategory.NORMAL
            else -> BMICategory.OVERWEIGHT
        }
    }
}

enum class BMICategory {
    NORMAL,
    UNDERWEIGHT,
    OVERWEIGHT
}
