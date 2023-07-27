package com.codingtest.moviecodingtest.data.vo

sealed class BaseResponse<T>(val data: T? = null, val code: String? = null, val message: String? = null) {
    class Success<T>(data: T) : BaseResponse<T>(data = data)
    class Error<T>(message: String) : BaseResponse<T>( message = message)
    class Loading<T> : BaseResponse<T>()
}