package com.alibaba.android.arouter.demo.kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_kotlin_autowired.*

@Route(path = "/module_kotlin/autowired")
class KotlinAutowiredActivity : AppCompatActivity() {

    @Autowired
    @JvmField
    var name: String? = null

    @Autowired
    @JvmField
    var age: Int? = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)  // Start auto inject.

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_autowired)

        content.text = "name = $name, age = $age"
    }
}
