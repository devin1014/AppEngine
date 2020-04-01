package com.alibaba.android.arouter.demo.kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import com.alibaba.android.arouter.demo.kotlin.R.layout
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.android.synthetic.main.activity_kotlin_autowired.*

@Route(path = "/module_kotlin/inherit_wired")
class KotlinInheritActivity : KotlinBaseActivity() {

    @Autowired(required = true)
    @JvmField
    var test: String? = null

    @Autowired
    @JvmField
    var name: String? = null

    @Autowired
    @JvmField
    var age: Int? = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this, { list ->
            Toast.makeText(this, "autowired failed: $list", Toast.LENGTH_SHORT).show()
        }, true)

        super.onCreate(savedInstanceState)
        setContentView(layout.activity_kotlin_inherit)

        content.text = "parentName = $parentName, parentAge = $parentAge \nname = $name, age = $age"
    }
}