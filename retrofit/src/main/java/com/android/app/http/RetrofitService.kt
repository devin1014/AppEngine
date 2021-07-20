package com.android.app.http

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import kotlin.reflect.KClass

@Suppress("ObjectPropertyName")
object RetrofitService {

    const val KEY_BASE_URL="baseUrl"

    private val defaultHttpClient: OkHttpClient by lazy { OkHttpClient.Builder().build() }

    var httpClient: OkHttpClient? = null
    var baseUrl: String = ""

    private var _retrofit: Retrofit? = null
    private val retrofit: Retrofit
        get() {
            if (_retrofit == null) {
                _retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient ?: defaultHttpClient)
                    .addConverterFactory(CustomGsonConvert())
                    .build()
            }
            return _retrofit!!
        }

    fun <T> get(t: Class<T>): T = retrofit.create(t)

    fun <T : Any> get(t: KClass<T>): T = get(t.java)
}
