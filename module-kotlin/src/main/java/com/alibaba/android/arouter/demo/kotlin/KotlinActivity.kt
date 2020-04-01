package com.alibaba.android.arouter.demo.kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.demo.kotlin.R.layout
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = "/module_kotlin/1")
class KotlinActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(layout.activity_kotlin)
    }
}