package com.alibaba.android.arouter.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter

@Route(path = Constants.ROUTER_ACTIVITY_MAIN)
class MainActivity : BaseActivity() {

    private val radioGroup: RadioGroup by lazy { findViewById(R.id.radio_group) }

    override fun getContentId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        initComponent()
        intentUri?.run { onRouter(this) }
    }

    private fun initComponent() {
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val tag = group.findViewById<RadioButton>(checkedId).tag as? String
            showFragment(ARouter.getInstance().build(tag).navigation() as? Fragment)
        }
        radioGroup.check(R.id.radio_fragment_a)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        ARouter.getInstance().inject(this)
        intentUri?.run { onRouter(this) }
    }

    override fun onRouter(uri: Uri) {
        when (uri.host) {
            "main_aa" -> radioGroup.check(R.id.radio_fragment_a)
            "main_bb" -> radioGroup.check(R.id.radio_fragment_b)
            "main_cc" -> radioGroup.check(R.id.radio_fragment_c)
            else -> {
            }
        }
    }

}