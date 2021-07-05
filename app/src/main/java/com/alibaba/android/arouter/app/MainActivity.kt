package com.alibaba.android.arouter.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RadioGroup.OnCheckedChangeListener
import com.alibaba.android.arouter.app.core.linkToFragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter

@Route(path = Constants.ROUTER_ACTIVITY_MAIN)
class MainActivity : BaseActivity(), OnCheckedChangeListener {

    private val radioGroup: RadioGroup by lazy { findViewById(R.id.radio_group) }

    override fun getContentId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        initComponent()
        dataUri?.run { onRouter(this) }
    }

    private fun initComponent() {
        radioGroup.setOnCheckedChangeListener(this)
        radioGroup.check(R.id.radio_fragment_a)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        ARouter.getInstance().inject(this)
        dataUri?.run { onRouter(this) }
    }

    override fun onCheckedChanged(group: RadioGroup, checkedId: Int) {
        val path = group.findViewById<RadioButton>(checkedId).tag as String
        replaceFragment(linkToFragment(path))
    }

    override fun onRouter(uri: Uri) {
        Log.i(Constants.TAG_LOG, "onRouter: $uri")
        replaceFragment(linkToFragment(uri))
//        when (uri.host) {
//            "main_aa" -> radioGroup.check(R.id.radio_fragment_a)
//            "main_bb" -> radioGroup.check(R.id.radio_fragment_b)
//            "main_cc" -> radioGroup.check(R.id.radio_fragment_c)
//            else -> {
//            }
//        }
    }

}