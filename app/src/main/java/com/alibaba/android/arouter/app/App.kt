package com.alibaba.android.arouter.app

import android.util.Log
import com.android.app.http.BaseUrlReplaceInterceptor
import com.android.app.http.RetrofitService
import com.android.appengine.ApplicationEngine
import com.android.appengine.room.RoomDatabase
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File

@Suppress("unused")
class App : ApplicationEngine() {

    override fun onApplicationCreate() {
        Log.i(Constants.TAG_LOG, "------------------------------------------------------------------")
        Log.i(Constants.TAG_LOG, "---- ${javaClass.simpleName} onCreate    ")
        Log.i(Constants.TAG_LOG, "---- cacheFile: $cacheDir")
        Log.i(Constants.TAG_LOG, "---- externalCacheDir: $externalCacheDir")
        Log.i(Constants.TAG_LOG, "------------------------------------------------------------------")

        logActivityFragmentLifecycle()

//        val proxyCallback = Proxy.newProxyInstance(
//            classLoader,
//            arrayOf(ActivityLifecycleCallbacks::class.java),
//            LogActivityLifecycleCallbacksProxy(ActivityLifecycleCallbacksImpl())
//        ) as ActivityLifecycleCallbacks
//        registerActivityLifecycleCallbacks(proxyCallback)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
            .addInterceptor(BaseUrlReplaceInterceptor())
//            .addInterceptor(CacheInterceptor())
            .cache(Cache(File(cacheDir, "okHttp-cache"), 1024 * 1024 * 10L))
            .build()
        RetrofitService.httpClient = okHttpClient
        RetrofitService.baseUrl = "https://wnba.neulion.com/"

        RoomDatabase.init(this)
    }
}