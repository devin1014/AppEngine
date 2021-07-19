package com.android.app.http.module

import com.android.app.http.ResponseResult
import retrofit2.http.GET
import retrofit2.http.Query

interface ScheduleHttpService {
    @GET("/schedule?format=json")
    suspend fun <T> getSchedule(@Query("day") day: String): ResponseResult<T>
}