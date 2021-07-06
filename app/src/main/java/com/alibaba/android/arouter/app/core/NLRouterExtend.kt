package com.alibaba.android.arouter.app.core

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.app.Constants
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
fun Postcard.transferDataUri(intent: Intent): Postcard {
    if (intent.data != null) {
        NLRouter.buildRouter(intent.data!!)?.run {
            withSerializable(Constants.EXTRA_KEY_ROUTER_URI, this)
        }
    }
    return this
}

fun Postcard.transferExtraParams(bundle: Bundle?): Postcard {
    if (bundle != null && bundle.size() > 0) {
        for (key in bundle.keySet()) {
            // ignore chrome browser data.
            if (key.startsWith("org.chromium")) continue
            bundle.get(key)?.run {
                setPostcardData(this@transferExtraParams, key, this)
            }
        }
    }
    return this
}

private fun setPostcardData(postcard: Postcard, key: String, value: Any) {
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
fun Activity.linkToActivity(path: String) {
    ARouter.getInstance().build(path)
        .transferDataUri(intent)
        .transferExtraParams(intent.extras)
        .navigation()
}

fun <T : Fragment> Activity.linkToFragment(path: String): T? {
    return ARouter.getInstance().build(path)
        .transferDataUri(intent)
        .transferExtraParams(intent.extras)
        .navigation() as? T
}

fun <T : Fragment> Activity.linkToFragment(uri: Uri): T? {
    return ARouter.getInstance().build(uri)
        .transferDataUri(intent)
        .transferExtraParams(intent.extras)
        .navigation() as? T
}

//fun <T : Fragment> Fragment.linkToFragment(path: String): T? {
//    return ARouter.getInstance().build(path)
//        .transferDataUri(requireActivity().intent)
//        .transferExtraParams(requireActivity().intent.extras)
//        .navigation() as? T
//}