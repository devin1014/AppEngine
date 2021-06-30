package com.alibaba.android.arouter.demo.kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import com.alibaba.android.arouter.demo.kotlin.R.layout
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_kotlin.*
import java.io.Serializable

@Route(path = "/module_kotlin/target2", group = "kotlin")
class KotlinGroupActivity : KotlinBaseActivity() {
    @Autowired
    @JvmField
    var key1: String? = null

    @Autowired
    @JvmField
    var key2: String? = null

    @Autowired
    @JvmField
    var obj: Serializable? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_kotlin)

        ARouter.getInstance().inject(this)
        key2 = intent.getStringExtra("key2")

        content.text = with(StringBuilder()) {
            append("name=$name\n")
            append("description=$description\n")
            append("key1=$key1\n")
            append("key2=$key2\n")
            append("obj=$obj\n")
        }.toString()
    }
}