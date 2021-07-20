package com.alibaba.android.arouter.app.service

import com.android.app.http.ResponseResult
import com.android.app.http.RetrofitService
import com.android.app.http.module.HttpService
import retrofit2.http.GET
import retrofit2.http.Headers

interface AppConfigService : HttpService {

    @Headers("${RetrofitService.KEY_BASE_URL}:http://mobile.neulion.net.cn/svn/projects")
    @GET("/wnba/2021/appconfig_android_dev.json")
    suspend fun <T> getAppConfig(): ResponseResult<T>

}