package com.android.appengine.util

import android.util.Log
import com.android.appengine.BuildConfig

object EngineLog {

    private const val EMPTY = ""
    var logTag: String = "AppEngine"
    var showLog = BuildConfig.DEBUG

    fun debug(message: String?) {
        if (showLog) Log.d(logTag, message ?: EMPTY)
    }

    fun info(message: String?) {
        if (showLog) Log.i(logTag, message ?: EMPTY)
    }

    fun warning(message: String?) {
        if (showLog) Log.w(logTag, message ?: EMPTY)
    }

    fun error(message: String?) {
        if (showLog) Log.e(logTag, message ?: EMPTY)
    }

    fun error(message: String?, e: Throwable?) {
        if (showLog) Log.e(logTag, message ?: e?.toString() ?: EMPTY)
    }
}