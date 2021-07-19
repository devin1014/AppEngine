package com.android.app.http.module

import com.android.app.http.ResponseResult

interface HttpService

//    companion object {
//        suspend fun <T : Any, R> get(t: KClass<T>, block: suspend (T) -> ResponseResult<R>): ResponseResult<R> {
//            return safetyCall { block(RetrofitService.get(t)) }
//        }
//    }
//
//suspend fun <T : HttpService, R> KClass<T>.retrofit(block: suspend T.() -> ResponseResult<R>): ResponseResult<R> {
//    return block(RetrofitService.get(this))
//}
//
//suspend fun <T : HttpService, R> KClass<T>.call(block: suspend T.() -> ResponseResult<R>): ResponseResult<R> {
//    return block(RetrofitService.get(this))
//}

suspend fun <T> safetyCall(block: suspend () -> ResponseResult<T>): ResponseResult<T> {
    return try {
        block()
    } catch (e: Exception) {
        ResponseResult.failed(e)
    }
}
