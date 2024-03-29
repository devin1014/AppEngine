package com.alibaba.android.arouter.app.service

import android.net.Uri
import com.alibaba.android.arouter.app.Constants
import com.android.appengine.router.RouterInfo
import com.android.appengine.router.RouterParseService
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = "/app/service/router_parse")
class RouterParseServiceImpl : RouterParseService {

    override var scheme: String = "scheme"

    override fun parseRouterUri(uri: Uri): RouterInfo? {
        val routerInfo: RouterInfo? = when (uri.host) {
            "main_home" -> RouterInfo().apply {
                activity = Constants.ROUTER_ACTIVITY_MAIN
                fragment = Constants.ROUTER_FRAGMENT_HOME
            }
            "main_schedule" -> RouterInfo().apply {
                activity = Constants.ROUTER_ACTIVITY_MAIN
                fragment = Constants.ROUTER_FRAGMENT_SCHEDULE
            }
            "main_settings" -> RouterInfo().apply {
                activity = Constants.ROUTER_ACTIVITY_MAIN
                fragment = Constants.ROUTER_FRAGMENT_SETTINGS
            }
            "account" -> {
                RouterInfo().apply {
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
                    routerInfo.params[key] = parseQueryObjectType(uri.host, key, this)
                }
            }
        }
        return routerInfo
    }

    private fun parseQueryObjectType(host: String?, key: String, value: String): Any {
        if ("main_schedule" == host && key == "position") {
            return try {
                value.toInt()
            } catch (e: Exception) {
                "0"
            }
        }
        return value
    }
}