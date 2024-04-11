package com.mlbench.tesla.repository



sealed class NetworkResult<T>(val data: T? = null, val message: String? = null ,val code: Int? = null) {

    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(message: String?,code: Int?=null, data: T? = null) : NetworkResult<T>(data, message,code)
    class Loading<T> : NetworkResult<T>()

}