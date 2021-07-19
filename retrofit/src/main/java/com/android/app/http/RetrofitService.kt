package com.android.app.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import kotlin.reflect.KClass

class RetrofitService private constructor() {

    @Suppress("ObjectPropertyName")
    companion object {

        private val defaultHttpClient: OkHttpClient
            get() = OkHttpClient.Builder().build()

        var httpClient: OkHttpClient? = null

        private var _retrofit: Retrofit? = null
        private val retrofit: Retrofit
            get() {
                if (_retrofit == null) {
                    _retrofit = Retrofit.Builder()
                        .baseUrl("https://wnba.neulion.com/")
                        .client(httpClient ?: defaultHttpClient)
                        .addConverterFactory(CustomGsonConvert())
                        .build()
                }
                return _retrofit!!
            }

        fun <T> get(t: Class<T>): T {
            return retrofit.create(t)
        }

        fun <T : Any> get(t: KClass<T>): T {
            return get(t.java)
        }
    }
}
