package com.alibaba.android.arouter.app.core

import android.app.Application
import com.alibaba.android.arouter.app.BuildConfig
import com.alibaba.android.arouter.launcher.ARouter

object NLRouter {

    fun init(context: Application) {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            //ARouter.printStackTrace()
        }
        ARouter.init(context)
    }

//    fun buildRouterUri(path: String, query: Map<String, String>? = null): Uri {
//        val hostPathBuilder = with(StringBuilder()) {
//            append("http://")
//            append(routerHost)
//            append(path)
//        }
//        val queryBuilder = with(StringBuilder()) {
//            if (!query.isNullOrEmpty()) {
//                if (length > 0) append("&")
//                for ((key, value) in query) {
//                    append("$key=$value")
//                }
//            }
//        }
//        return Uri.parse("$hostPathBuilder?$queryBuilder")
//    }
}