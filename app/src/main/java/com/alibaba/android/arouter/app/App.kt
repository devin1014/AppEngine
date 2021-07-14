package com.alibaba.android.arouter.app

import android.util.Log
import com.android.appengine.ApplicationEngine

@Suppress("unused")
class App : ApplicationEngine() {

    override fun onApplicationCreate() {
        Log.i(Constants.TAG_LOG, "------------------------------------------------------------------")
        Log.i(Constants.TAG_LOG, "---- ${javaClass.simpleName} onCreate    ")
        Log.i(Constants.TAG_LOG, "------------------------------------------------------------------")

        logActivityFragmentLifecycle()

//        val proxyCallback = Proxy.newProxyInstance(
//            classLoader,
//            arrayOf(ActivityLifecycleCallbacks::class.java),
//            LogActivityLifecycleCallbacksProxy(ActivityLifecycleCallbacksImpl())
//        ) as ActivityLifecycleCallbacks
//        registerActivityLifecycleCallbacks(proxyCallback)
    }
}