package com.alibaba.android.arouter.app

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.android.appengine.router.Router

@Suppress("unused")
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.i(Constants.TAG_LOG, "------------------------------------------------------------------")
        Log.i(Constants.TAG_LOG, "------------------------------------------------------------------")
        Log.i(Constants.TAG_LOG, "Application onCreate")

        registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Log.d(Constants.TAG_LOG, "onActivityCreated: ${activity.javaClass.simpleName}")
                if (activity is FragmentActivity) {
                }
            }

            override fun onActivityStarted(activity: Activity) {
                Log.d(Constants.TAG_LOG, "onActivityStarted: ${activity.javaClass.simpleName}")
            }

            override fun onActivityResumed(activity: Activity) {
                Log.d(Constants.TAG_LOG, "onActivityResumed: ${activity.javaClass.simpleName}")
            }

            override fun onActivityPaused(activity: Activity) {
                Log.d(Constants.TAG_LOG, "onActivityPaused: ${activity.javaClass.simpleName}")
            }

            override fun onActivityStopped(activity: Activity) {
                Log.d(Constants.TAG_LOG, "onActivityStopped: ${activity.javaClass.simpleName}")
            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
            }

            override fun onActivityDestroyed(activity: Activity) {
                Log.d(Constants.TAG_LOG, "onActivityDestroyed: ${activity.javaClass.simpleName}")
            }
        })

        Router.init(this)
    }
}