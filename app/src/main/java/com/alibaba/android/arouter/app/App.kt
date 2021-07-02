package com.alibaba.android.arouter.app

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import java.io.Serializable
import kotlin.reflect.KClass

@Suppress("unused")
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ARouter.openLog()
        ARouter.printStackTrace()
        ARouter.init(this)
    }
}

fun <T : Any> getAppService(service: KClass<out T>): T = getAppService(service.java)

fun <T> getAppService(service: Class<out T>): T = ARouter.getInstance().navigation(service)

fun Intent.transfer(postcard: Postcard): Intent {
    if (data != null) {
        postcard.withParcelable("_intentDataUri", data)
    }
    val bundle = extras
    if (bundle != null && bundle.size() > 0) {
        for (key in bundle.keySet()) {
            bundle.get(key)?.run {
                setPostcardData(postcard, key, this)
            }
        }
    }
    return this
}

fun Postcard.transfer(intent: Intent): Postcard {
    intent.transfer(this)
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

fun Activity.linkToActivity(path: String) {
    ARouter.getInstance()
        .build(path)
        .transfer(intent)
        .navigation()
}