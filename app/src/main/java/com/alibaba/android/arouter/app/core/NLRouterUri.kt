package com.alibaba.android.arouter.app.core

import android.net.Uri
import java.io.Serializable

class NLRouterUri(val host: String, val path: String = "") : Serializable {

    private var query: MutableMap<String, String>? = null

    internal fun buildQuery(uri: Uri): NLRouterUri {
        if (!uri.query.isNullOrEmpty()) {
            for (key in uri.queryParameterNames) {
                if (query == null) query = mutableMapOf()
                query!![key] = uri.getQueryParameter(key)!!
            }
        }
        return this
    }

    override fun toString(): String {
        return "NLRouterUri{ " +
                "host=$host, " +
                "path=$path, " +
                "query=${query.toString()} " +
                "}"
    }
}