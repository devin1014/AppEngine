package com.alibaba.android.arouter.app.util

import android.app.Activity
import android.util.Log

class Utils private constructor() {
    companion object {
        fun printIntentInfo(activity: Activity) {
            Log.i("AppRouter", "---------------------------------")
            Log.i("AppRouter", activity.javaClass.simpleName)
            Log.i("AppRouter", "data: ${activity.intent.data}")
            activity.intent.extras?.keySet()?.run {
                Log.i("AppRouter", "extras: ${this.size}")
                for (key in this) {
                    val obj = activity.intent!!.extras!!.get(key)
                    Log.i("AppRouter", "    $key = $obj")
                }
            }

        }
    }
}