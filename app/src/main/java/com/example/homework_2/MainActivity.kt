package com.example.homework_2

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

//Calculator Kotlin
open class MainActivity : AppCompatActivity() {
    val init = InitView()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calcView()
    }

    fun calcView() {
        bt_button_0.setOnClickListener { setTextField(init.bt0()) }
        bt_button_1.setOnClickListener { setTextField(init.bt1()) }
        bt_button_2.setOnClickListener { setTextField(init.bt2()) }
        bt_button_3.setOnClickListener { setTextField(init.bt3()) }
        bt_button_4.setOnClickListener { setTextField(init.bt4()) }
        bt_button_5.setOnClickListener { setTextField(init.bt5()) }
        bt_button_6.setOnClickListener { setTextField(init.bt6()) }
        bt_button_7.setOnClickListener { setTextField(init.bt7()) }
        bt_button_8.setOnClickListener { setTextField(init.bt8()) }
        bt_button_9.setOnClickListener { setTextField(init.bt9()) }
        bt_button_sum.setOnClickListener { setTextField(init.btSum()) }
        bt_button_minus.setOnClickListener { setTextField(init.btMinus()) }
        bt_button_multiply.setOnClickListener { setTextField(init.btMult()) }
        bt_button_split.setOnClickListener { setTextField(init.btSplit()) }
        bt_break_open.setOnClickListener { setTextField(init.btOpen()) }
        bt_break_close.setOnClickListener { setTextField(init.btClose()) }
        bt_button_dot.setOnClickListener { setTextField(init.btDot()) }

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
                if (result == longRes.toDouble())
                    tv_result.text = longRes.toString()
                else
                    tv_result.text = result.toString()
            } catch (e: Exception) {
                val d = Log.d(getString(R.string.Error), "message: ${e.message}")
            }
        }
    }

    fun setTextField(str: String) {
        if (tv_result.text != "") {
            tv_editText.text = tv_result.text
            tv_result.text = ""
        }
        tv_editText.append(str)
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.run {
            putString("KEY", tv_editText.text.toString())
            putString("KEY", tv_result.text.toString())
        }
        super.onSaveInstanceState(savedInstanceState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        tv_editText.text = savedInstanceState.getString("KEY")
        tv_result.text = savedInstanceState.getString("KEY")
    }
}