package com.alibaba.android.arouter.app.util

import android.app.Activity
import android.app.Application.ActivityLifecycleCallbacks
import com.android.appengine.util.EngineLog
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class LogActivityLifecycleCallbacksProxy(private val callback: ActivityLifecycleCallbacks) : InvocationHandler {
    override fun invoke(proxy: Any, method: Method, args: Array<Any?>): Any? {
        EngineLog.debug((args[0] as Activity).javaClass.simpleName + " " + method.name.replace("Activity", ""))
        return method.invoke(callback, *args)
    }
}