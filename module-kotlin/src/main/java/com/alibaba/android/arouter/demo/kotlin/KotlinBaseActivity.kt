package com.alibaba.android.arouter.demo.kotlin

import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Autowired

abstract class KotlinBaseActivity : AppCompatActivity() {
    @Autowired
    @JvmField
    var parentName: String? = null

    @Autowired
    @JvmField
    var parentAge: Int? = 0
}