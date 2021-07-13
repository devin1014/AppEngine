package com.alibaba.android.arouter.app

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.android.appengine.router.RouterInfo
import com.android.appengine.router.RouterParseService
import com.android.appengine.router.buildActivity
import com.android.appengine.router.getAppService
import com.alibaba.android.arouter.app.service.AuthService
import com.alibaba.android.arouter.app.util.Utils

//FIXME: 1.launch app and touch home button, relaunch app from desktop icon, this activity not recreated!
//FIXME: 1.launch app and touch home button, relaunch app from desktop icon, this activity not recreated!
//FIXME: 1.launch app and touch home button, relaunch app from desktop icon, this activity not recreated!
class LaunchDispatchActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.printIntentInfo(this, "onCreate")
        finish()
        if (getAppService(AuthService::class).initialized) {
            if (intent.action == Intent.ACTION_MAIN && intent.hasCategory(Intent.CATEGORY_LAUNCHER)
            ) { // open main page when user launch app from desktop
                buildActivity(Constants.ROUTER_ACTIVITY_MAIN)
            } else {
                val routerInfo: RouterInfo? = intent.data?.let { uri ->
                    getAppService(RouterParseService::class).parse(uri)
                }
                if (routerInfo != null) {
                    buildActivity(routerInfo)
                }
            }
        } else {
            buildActivity(Constants.ROUTER_ACTIVITY_SPLASH, true)
        }
    }
}