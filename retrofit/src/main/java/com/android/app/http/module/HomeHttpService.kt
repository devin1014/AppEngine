@file:Suppress("SpellCheckingInspection")

package com.android.app.http.module

import com.android.app.http.ResponseResult
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeHttpService : HttpService {
    @GET("home?format=json")
    suspend fun <T> getHomeResult(
        @Query("nosports") nosports: String = "true",
        @Query("nocatstruct") nocatstruct: String = "true"
    ): ResponseResult<T>
}