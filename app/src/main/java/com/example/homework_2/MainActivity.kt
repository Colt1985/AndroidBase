package com.example.homework_2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_button_0.setOnClickListener { setTextField("0") }
        bt_button_1.setOnClickListener { setTextField("1") }
        bt_button_2.setOnClickListener { setTextField("2") }
        bt_button_3.setOnClickListener { setTextField("3") }
        bt_button_4.setOnClickListener { setTextField("4") }
        bt_button_5.setOnClickListener { setTextField("5") }
        bt_button_6.setOnClickListener { setTextField("6") }
        bt_button_7.setOnClickListener { setTextField("7") }
        bt_button_8.setOnClickListener { setTextField("8") }
        bt_button_9.setOnClickListener { setTextField("9") }

        bt_button_sum.setOnClickListener { setTextField("+") }
        bt_button_minus.setOnClickListener { setTextField("-") }
        bt_button_multiply.setOnClickListener { setTextField("*") }
        bt_button_split.setOnClickListener { setTextField("/") }
        bt_break_open.setOnClickListener { setTextField("(") }
        bt_break_close.setOnClickListener { setTextField(")") }

        bt_clear.setOnClickListener {
            tv_editText.text = ""
            tv_result.text = ""
        }
        bt_back.setOnClickListener {
            val str = tv_editText.text.toString()
            if (str.isNotEmpty())
                tv_editText.text = str.substring(0, str.length - 1)
            tv_result.text = ""
        }

        bt_equals.setOnClickListener {
            try {
                val ex = ExpressionBuilder(tv_editText.text.toString()).build()
                val result = ex.evaluate()

                val longRes = result.toLong()
                if(result == longRes.toDouble())
                    tv_result.text = longRes.toString()
                else
                    tv_result.text = result.toString()
            } catch (e: Exception) {
                Log.d("ошибка", "сообщение: ${e.message}")
            }
        }
    }
    fun setTextField(str: String){
        if(tv_result.text != ""){
            tv_editText.text = tv_result.text
            tv_result.text = ""
        }
        tv_editText.append(str)
    }
}