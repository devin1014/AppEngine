package com.alibaba.android.arouter.app.fragment

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.app.Constants
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter

@Route(path = Constants.ROUTER_FRAGMENT_HOME, group = "main")
class HomeFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ARouter.getInstance().inject(this)
        setContentText()
    }
}