package com.alibaba.android.arouter.app.fragment

import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.alibaba.android.arouter.app.Constants
import com.alibaba.android.arouter.app.R
import com.alibaba.android.arouter.app.util.AppLog
import com.alibaba.android.arouter.facade.annotation.Route
import com.android.appengine.room.RoomDatabase
import com.android.appengine.router.buildActivity

@Route(path = Constants.ROUTER_FRAGMENT_SETTINGS)
class SettingsFragment : BaseFragment() {

    override fun getContentId(): Int = R.layout.fragment_settings

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val onClickListener = OnClickListener {
            requireActivity().buildActivity {
                activity = Constants.ROUTER_ACTIVITY_ACCOUNT
                fragment = when (it.id) {
                    R.id.settings_btn_signin -> Constants.ROUTER_FRAGMENT_AUTH_SIGNIN
                    R.id.settings_btn_register -> Constants.ROUTER_FRAGMENT_AUTH_REGISTER
                    else -> Constants.ROUTER_FRAGMENT_AUTH_PURCHASE
                }
            }
        }
        view.findViewById<Button>(R.id.settings_btn_signin).setOnClickListener(onClickListener)
        view.findViewById<Button>(R.id.settings_btn_register).setOnClickListener(onClickListener)
        view.findViewById<Button>(R.id.settings_btn_purchase).setOnClickListener(onClickListener)

        lifecycleScope.launchWhenResumed {
            val result = RoomDatabase.database.urlParamDao().getUrlParams()
            if(result.isNotEmpty()){
                AppLog.info("result: ${result.size}")
            }
        }
    }
}