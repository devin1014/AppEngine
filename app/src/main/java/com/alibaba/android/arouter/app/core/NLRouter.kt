package com.alibaba.android.arouter.app.core

import android.app.Activity
import android.app.Application
import android.net.Uri
import com.alibaba.android.arouter.app.BuildConfig
import com.alibaba.android.arouter.app.Constants
import com.alibaba.android.arouter.launcher.ARouter

object NLRouter {

    interface OnRouter {
        fun onRouter(routerUri: NLRouterUri): Boolean
    }

    fun init(context: Application) {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            //ARouter.printStackTrace()
        }
        ARouter.init(context)
    }

    fun route(activity: Activity): Boolean {
        var routerUri: NLRouterUri? = activity.intent.data?.let { buildRouter(it) }
        if (routerUri == null) {
            routerUri = activity.intent.getSerializableExtra(Constants.EXTRA_KEY_ROUTER_URI) as? NLRouterUri
        }
        if (routerUri != null) {
            ARouter.getInstance()
                .build(routerUri.host)
                .withSerializable(Constants.EXTRA_KEY_ROUTER_URI, routerUri)
                .navigation()
            return true
        }
        return false
    }

    fun buildRouter(uri: Uri): NLRouterUri? {
        val routerUri = when (uri.host) {
            "main_home" -> NLRouterUri(Constants.ROUTER_ACTIVITY_MAIN, Constants.ROUTER_FRAGMENT_HOME)
            "main_schedule" -> NLRouterUri(Constants.ROUTER_ACTIVITY_MAIN, Constants.ROUTER_FRAGMENT_SCHEDULE)
            "main_settings" -> NLRouterUri(Constants.ROUTER_ACTIVITY_MAIN, Constants.ROUTER_FRAGMENT_SETTINGS)
            "account" -> {
                val path = if (uri.path?.contains("signIn") == true) Constants.ROUTER_FRAGMENT_AUTH_SIGNIN else Constants.ROUTER_FRAGMENT_AUTH_REGISTER
                NLRouterUri(Constants.ROUTER_ACTIVITY_ACCOUNT, path)
            }
            else -> return null
        }
        routerUri.buildQuery(uri)
        return routerUri
    }
}