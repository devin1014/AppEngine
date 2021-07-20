package com.android.app.http

sealed class ResponseResult<out R> {
    companion object {
        fun <T> success(data: T): ResponseResult<T> = Success(data)
        fun failed(exception: Exception): ResponseResult<Nothing> = Failed(exception)
    }

    val isSuccess: Boolean get() = this is Success

    val isFailure: Boolean get() = this is Failed

    fun getData(): R = if (isSuccess) (this as Success).value else throw IllegalArgumentException((this as Failed)._exception)

    fun getException(): Exception = if (isFailure) (this as Failed)._exception else emptyException

    override fun toString(): String {
        return when {
            isSuccess -> (this as Success).value.toString()
            isFailure -> (this as Failed)._exception.toString()
            else -> "Invalid Data!"
        }
    }

    private val emptyException = Exception("this is empty exception,please ignore!")

    internal data class Success<out T>(val value: T) : ResponseResult<T>()
    internal data class Failed(val _exception: Exception) : ResponseResult<Nothing>()
}