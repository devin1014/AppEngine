package com.alibaba.android.arouter.app.core

import android.app.Application
import android.net.Uri
import com.alibaba.android.arouter.app.BuildConfig
import com.alibaba.android.arouter.launcher.ARouter
import java.io.Serializable

object NLRouter {

    const val EXTRA_KEY_PATH = "_path"
    const val EXTRA_KEY_URI = "_uri"
    const val EXTRA_KEY_PENDING_DATA = "_pendingData"
    const val EXTRA_KEY_ROUTER_INFO = "_routerInfo"

    interface OnRouter {
        fun onRouter(routerUri: NLRouterInfo): Boolean
    }

    interface RouterParser {
        fun parse(uri: Uri): NLRouterInfo?
    }

    var parser: RouterParser? = null

    fun init(context: Application, parser: RouterParser? = null) {
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            //ARouter.printStackTrace()
        }
        ARouter.init(context)
        this.parser = parser
    }
}

class NLRouterInfo internal constructor() : Serializable {
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