package com.example.myapplication

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private   var instanceState:Bundle?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        instanceState=savedInstanceState
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        currentFocus?.let {
            val imm: InputMethodManager = getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as (InputMethodManager)
            imm.hideSoftInputFromWindow(it.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

}