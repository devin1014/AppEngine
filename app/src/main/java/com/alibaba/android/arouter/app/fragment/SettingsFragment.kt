package com.alibaba.android.arouter.app.fragment

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import com.alibaba.android.arouter.app.Constants
import com.alibaba.android.arouter.app.R
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter

@Route(path = Constants.ROUTER_FRAGMENT_SETTINGS)
class SettingsFragment : BaseFragment() {

    override fun getContentId(): Int = R.layout.fragment_settings

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ARouter.getInstance().inject(this)
        val onClick = OnClickListener {
            ARouter.getInstance().build(Constants.ROUTER_ACTIVITY_ACCOUNT).navigation()
        }
        view.findViewById<Button>(R.id.settings_signin).setOnClickListener(onClick)
        view.findViewById<Button>(R.id.settings_register).setOnClickListener(onClick)
    }
}