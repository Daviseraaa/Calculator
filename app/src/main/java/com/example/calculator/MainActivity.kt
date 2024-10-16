package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var firstNumber: Int = 0
    private var secondNumber: Int = 0
    private var operator: String? = null
    private var isSecondNumber: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewResult: TextView = findViewById(R.id.textViewResult)

        val buttons = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3,
            R.id.button4, R.id.button5, R.id.button6, R.id.button7,
            R.id.button8, R.id.button9
        )

        // Thiết lập các sự kiện cho nút số
        for (buttonId in buttons) {
            val button: Button = findViewById(buttonId)
            button.setOnClickListener {
                val number = button.text.toString().toInt()
                if (isSecondNumber) {
                    secondNumber = secondNumber * 10 + number
                    textViewResult.text = secondNumber.toString()
                } else {
                    firstNumber = firstNumber * 10 + number
                    textViewResult.text = firstNumber.toString()
                }
            }
        }

        // Thiết lập sự kiện cho các nút phép toán
        findViewById<Button>(R.id.buttonAdd).setOnClickListener {
            operator = "+"
            isSecondNumber = true
        }

        findViewById<Button>(R.id.buttonSubtract).setOnClickListener {
            operator = "-"
            isSecondNumber = true
        }

        findViewById<Button>(R.id.buttonMultiply).setOnClickListener {
            operator = "*"
            isSecondNumber = true
        }

        findViewById<Button>(R.id.buttonDivide).setOnClickListener {
            operator = "/"
            isSecondNumber = true
        }

        // Nút dấu bằng để thực hiện tính toán
        findViewById<Button>(R.id.buttonResult).setOnClickListener {
            val result = when (operator) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "*" -> firstNumber * secondNumber
                "/" -> if (secondNumber != 0) firstNumber / secondNumber else 0
                else -> 0
            }
            textViewResult.text = result.toString()
            // Đặt lại trạng thái
            firstNumber = result
            secondNumber = 0
            isSecondNumber = false
        }

        // Nút C (Clear Entry) để xóa dữ liệu
        findViewById<Button>(R.id.buttonC).setOnClickListener {
            firstNumber = 0
            secondNumber = 0
            isSecondNumber = false
            operator = null
            textViewResult.text = "0"
        }

        // Nút +/- để đổi dấu số hiện tại
        findViewById<Button>(R.id.buttonSign).setOnClickListener {
            if (isSecondNumber) {
                secondNumber = -secondNumber
                textViewResult.text = secondNumber.toString()
            } else {
                firstNumber = -firstNumber
                textViewResult.text = firstNumber.toString()
            }
        }
    }
}
