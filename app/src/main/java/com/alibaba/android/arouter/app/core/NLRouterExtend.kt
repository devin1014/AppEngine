package com.alibaba.android.arouter.app.core

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.app.BaseActivity
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import java.io.Serializable
import kotlin.reflect.KClass

// ---------------------------------------------------------------------
// ---- Service
// ---------------------------------------------------------------------
fun <T : Any> getAppService(service: KClass<out T>): T = getAppService(service.java)

fun <T> getAppService(service: Class<out T>): T = ARouter.getInstance().navigation(service)

// ---------------------------------------------------------------------
// ---- Extras
// ---------------------------------------------------------------------
fun Postcard.transferPendingData(intent: Intent): Postcard {
    if (intent.data != null) {
        withParcelable(NLRouter.EXTRA_KEY_PENDING_DATA, intent.data)
    }
    return this
}

fun Postcard.transferExtraParams(bundle: Bundle?): Postcard {
    if (bundle != null && bundle.size() > 0) {
        for (key in bundle.keySet()) {
            // ignore chrome browser data.
            if (key.startsWith("org.chromium")) continue
            if (key.startsWith("com.android.browser")) continue
            if (key.startsWith(Intent.EXTRA_REFERRER)) continue
            bundle.get(key)?.run {
                setPostcardData(this@transferExtraParams, key, this)
            }
        }
    }
    return this
}

fun Postcard.transferExtraParams(routerInfo: NLRouterInfo?): Postcard {
    if (routerInfo != null) {
        setPostcardData(this@transferExtraParams, NLRouter.EXTRA_KEY_ROUTER_INFO, routerInfo)
        if (routerInfo.params.isNotEmpty()) {
            for ((key, value) in routerInfo.params) {
                // ignore chrome browser data.
                if (key.startsWith("org.chromium")) continue
                if (key.startsWith("com.android.browser")) continue
                if (key.startsWith(Intent.EXTRA_REFERRER)) continue
                setPostcardData(this@transferExtraParams, key, value)
            }
        }
    }
    return this
}

private fun setPostcardData(postcard: Postcard, key: String, value: Any?) {
    if (value == null) return
    when (value) {
        is Byte -> postcard.withByte(key, value)
        is Int -> postcard.withInt(key, value)
        is Float -> postcard.withFloat(key, value)
        is Double -> postcard.withDouble(key, value)
        is Long -> postcard.withLong(key, value)
        is Char -> postcard.withChar(key, value)
        is String -> postcard.withString(key, value)
        is Bundle -> postcard.withBundle(key, value)
        is Serializable -> postcard.withSerializable(key, value)
        is Parcelable -> postcard.withParcelable(key, value)
        else -> postcard.withObject(key, value)
    }
}

// ---------------------------------------------------------------------
// ---- Navigate
// ---------------------------------------------------------------------
fun Activity.buildActivity(path: String, pendingData: Boolean = false) {
    val postcard = ARouter.getInstance().build(path)
    if (pendingData) postcard.transferPendingData(intent)
    postcard
        .transferExtraParams(intent.extras)
        .withString(NLRouter.EXTRA_KEY_PATH, path)
        .navigation()
}

fun Activity.buildActivity(lambda: NLRouterInfo.() -> Unit) {
    buildActivity(NLRouterInfo().apply(lambda))
}

fun Activity.buildActivity(info: NLRouterInfo) {
    ARouter.getInstance()
        .build(info.activity)
        .transferExtraParams(intent.extras)
        .transferExtraParams(info)
        .withString(NLRouter.EXTRA_KEY_PATH, info.activity)
        .navigation()
}

fun <T : Fragment> BaseActivity.buildFragment(path: String): T? {
    return ARouter.getInstance()
        .build(path)
        .transferExtraParams(intent.extras)
        .withString(NLRouter.EXTRA_KEY_PATH, path)
        .navigation() as? T
}
