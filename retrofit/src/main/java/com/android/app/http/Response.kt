package com.android.app.http

sealed class ResponseResult<out R> {
    companion object {
        fun <T> success(data: T): ResponseResult<T> = Success(data)
        fun failed(exception: Exception): ResponseResult<Nothing> = Error(exception)
    }

    val isSuccess: Boolean get() = this is Success

    val isFailure: Boolean get() = this is Error

    override fun toString(): String {
        return when {
            isSuccess -> (this as Success).value.toString()
            isFailure -> (this as Error).exception.toString()
            else -> "Invalid Data!"
        }
    }

    internal data class Success<out T>(val value: T) : ResponseResult<T>()
    internal data class Error(val exception: Exception) : ResponseResult<Nothing>()
}