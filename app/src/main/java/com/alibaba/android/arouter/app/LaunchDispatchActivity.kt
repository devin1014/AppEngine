package com.alibaba.android.arouter.app

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.alibaba.android.arouter.app.core.NLRouter
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
        Utils.printIntentInfo(this)
        finish()
        if (getAppService(AuthService::class).initialized) {
            if (!NLRouter.route(this)
                && intent.action == Intent.ACTION_MAIN && intent.hasCategory(Intent.CATEGORY_LAUNCHER)
            ) { // open main page when user launch app from desktop
                linkToActivity(Constants.ROUTER_ACTIVITY_MAIN)
            }
        } else {
            linkToActivity(Constants.ROUTER_ACTIVITY_SPLASH)
        }
    }
}