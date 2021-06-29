package com.alibaba.android.arouter.demo.kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import com.alibaba.android.arouter.demo.kotlin.R.layout
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.activity_kotlin_2.*

@Route(path = "/module_kotlin/2")
class Kotlin2Activity : KotlinBaseActivity() {

    @Autowired
    @JvmField
    var obj: Any? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_kotlin_2)

        content.text = "obj: $obj, name:$name, description:$description"
    }
}