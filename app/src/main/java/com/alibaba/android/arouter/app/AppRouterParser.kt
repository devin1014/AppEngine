package com.alibaba.android.arouter.app

import android.net.Uri
import com.alibaba.android.arouter.app.core.NLRouter.RouterParser
import com.alibaba.android.arouter.app.core.NLRouterInfo

class AppRouterParser : RouterParser {

    override fun parse(uri: Uri): NLRouterInfo? {
        val routerInfo: NLRouterInfo? = when (uri.host) {
            "main_home" -> NLRouterInfo().apply {
                activity = Constants.ROUTER_ACTIVITY_MAIN
                fragment = Constants.ROUTER_FRAGMENT_HOME
            }
            "main_schedule" -> NLRouterInfo().apply {
                activity = Constants.ROUTER_ACTIVITY_MAIN
                fragment = Constants.ROUTER_FRAGMENT_SCHEDULE
            }
            "main_settings" -> NLRouterInfo().apply {
                activity = Constants.ROUTER_ACTIVITY_MAIN
                fragment = Constants.ROUTER_FRAGMENT_SETTINGS
            }
            "account" -> {
                NLRouterInfo().apply {
                    activity = Constants.ROUTER_ACTIVITY_ACCOUNT
                    fragment = if (uri.path?.contains("signIn") == true)
                        Constants.ROUTER_FRAGMENT_AUTH_SIGNIN
                    else
                        Constants.ROUTER_FRAGMENT_AUTH_REGISTER
                }
            }
            else -> null
        }
        if (routerInfo != null && !uri.query.isNullOrEmpty()) {
            for (key in uri.queryParameterNames) {
                uri.getQueryParameter(key)?.run {
                    routerInfo.params[key] = this
                }
            }
        }
        return routerInfo
    }
}