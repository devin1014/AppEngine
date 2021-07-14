package com.android.appengine.router

import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import androidx.annotation.CallSuper
import com.alibaba.android.arouter.facade.template.IProvider
import com.alibaba.android.arouter.launcher.ARouter
import java.io.Serializable

object Router {

    const val EXTRA_KEY_PATH = "_path"
    const val EXTRA_KEY_PENDING_DATA = "_pendingData"
    const val EXTRA_KEY_ROUTER_INFO = "_routerInfo"

    interface OnRouter {
        fun onRouter(routerUri: RouterInfo): Boolean
    }
}

interface RouterParseService : IProvider {
    var scheme: String

    @CallSuper
    override fun init(context: Context) {
        try {
            val appInfo = context.packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
            scheme = appInfo.metaData.getString("routerScheme", "arouter")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun parse(uri: Uri): RouterInfo? = parseRouterUri(convertUri(uri))

    fun parseRouterUri(uri: Uri): RouterInfo?

    private fun convertUri(httpUri: Uri): Uri {
        if (!httpUri.scheme.isNullOrEmpty() && !httpUri.host.isNullOrEmpty()) {
            if ("http" == httpUri.scheme || "https" == httpUri.scheme) {
                val uri = Uri.parse(
                    httpUri.toString()
                        .replaceFirst(httpUri.scheme!!, scheme)
                        .replaceFirst("${httpUri.host!!}/", "")
                )
                ARouter.logger.info("convertUri", "oldUri= $httpUri")
                ARouter.logger.info("convertUri", "=======>")
                ARouter.logger.info("convertUri", "newUri= $uri")
                return uri
            }
        }
        return httpUri
    }
}

class RouterInfo : Serializable {
    var activity: String = ""
    var fragment: String = ""

    @Transient
    val params: MutableMap<String, Any> = mutableMapOf()

    override fun toString(): String {
        return "NLRouterInfo{ " +
                "activity:\'$activity\', " +
                "fragment:\'$fragment\' " +
                "}"
    }
}