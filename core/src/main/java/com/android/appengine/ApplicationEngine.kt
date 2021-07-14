package com.android.appengine

import android.app.Application
import androidx.annotation.CallSuper
import com.alibaba.android.arouter.launcher.ARouter
import com.android.appengine.util.EngineLog
import com.android.appengine.util.LogLifecycleCallback

abstract class ApplicationEngine : Application() {

    @CallSuper
    final override fun onCreate() {
        super.onCreate()
        onBeforeBaseCreate()
        ARouter.init(this)
        onApplicationCreate()
    }

    protected open fun onBeforeBaseCreate() {}

    protected open fun onAfterBaseCreate() {}

    abstract fun onApplicationCreate()

    protected fun logRouter() {
        ARouter.openLog()
    }

    protected fun logRouterStackTrack() {
        ARouter.printStackTrace()
    }

    protected fun logActivityLifecycle() {
        EngineLog.showLog = true
        registerActivityLifecycleCallbacks(LogLifecycleCallback.activityFragmentCallbacks)
    }

    protected fun logActivityFragmentLifecycle() {
        EngineLog.showLog = true
        registerActivityLifecycleCallbacks(LogLifecycleCallback.activityFragmentCallbacks)
    }

    protected fun logActivityAllFragmentLifecycle() {
        EngineLog.showLog = true
        registerActivityLifecycleCallbacks(LogLifecycleCallback.activityAllFragmentCallbacks)
    }
}