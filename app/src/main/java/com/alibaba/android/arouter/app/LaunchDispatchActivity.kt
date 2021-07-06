package com.alibaba.android.arouter.app

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.app.core.getAppService
import com.alibaba.android.arouter.app.core.linkToActivity
import com.alibaba.android.arouter.app.service.AuthService
import com.alibaba.android.arouter.app.util.Utils

//FIXME: 1.launch app and touch home button, relaunch app from desktop icon, this activity not recreated!
//FIXME: 1.launch app and touch home button, relaunch app from desktop icon, this activity not recreated!
//FIXME: 1.launch app and touch home button, relaunch app from desktop icon, this activity not recreated!
class LaunchDispatchActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        Utils.printIntentInfo(this)
        val path: String = if (getAppService(AuthService::class).initialized) {
            Constants.ROUTER_ACTIVITY_MAIN
        } else {
            Constants.ROUTER_ACTIVITY_SPLASH
        }
        linkToActivity(path)
        finish()
    }
}