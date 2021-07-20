package com.android.app.http

import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.Interceptor
import okhttp3.Interceptor.Chain
import okhttp3.Response


class BaseUrlReplaceInterceptor : Interceptor {
    override fun intercept(chain: Chain): Response {
        val originalRequest = chain.request()
        val newBaseUrl = originalRequest.header(RetrofitService.KEY_BASE_URL)?.toHttpUrlOrNull()
        return if (newBaseUrl != null) {
            val basePathSegments = originalRequest.url.encodedPath
            val newHttpUrl = originalRequest.url.newBuilder()
                .scheme(newBaseUrl.scheme)
                .host(newBaseUrl.host)
                .port(newBaseUrl.port)
                .encodedPath((newBaseUrl.encodedPath + basePathSegments).replace("//", "/"))
                .build()
            val newRequest = originalRequest.newBuilder()
                .removeHeader("baseUrl")
                .url(newHttpUrl)
                .build()
            chain.proceed(newRequest)
        } else {
            chain.proceed(originalRequest)
        }
    }
}