package com.alibaba.android.arouter.app.util

import android.app.Activity
import android.util.Log
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.app.Constants
import java.util.*

class Utils private constructor() {
    companion object {

        fun printIntentInfo(activity: Activity, functionName: String) {
            Log.i(Constants.TAG_LOG, "    ")
            Log.i(Constants.TAG_LOG, "------------------------------------------------------------------")
            Log.i(Constants.TAG_LOG, "---- ${activity.javaClass.simpleName} ----")
            Log.i(Constants.TAG_LOG, "$functionName, uri: ${activity.intent.data}")
            activity.intent.extras?.keySet()?.run {
                Log.i(Constants.TAG_LOG, "extras: ${this.size}")
                for (key in TreeSet(this)) { // order by first word
                    val obj = activity.intent!!.extras!!.get(key)
                    Log.i(Constants.TAG_LOG, "    $key = ${parseObj(obj)}")
                }
            }
        }

        fun printBundleInfo(fragment: Fragment) {
            Log.i(Constants.TAG_LOG, "    ")
            Log.i(Constants.TAG_LOG, "------------------------------------------------------------------")
            Log.i(Constants.TAG_LOG, "---- ${fragment.javaClass.simpleName} ----")
            fragment.arguments?.keySet()?.run {
                Log.i(Constants.TAG_LOG, "extras: ${this.size}")
                for (key in TreeSet(this)) { // order by first word
                    val obj = fragment.requireArguments().get(key)
                    Log.i(Constants.TAG_LOG, "    $key = ${parseObj(obj)}")
                }
            }
        }

        private fun parseObj(any: Any?): String {
            return if (any is String) "\'$any\'" else any?.toString() ?: "NULL"
        }
    }
}