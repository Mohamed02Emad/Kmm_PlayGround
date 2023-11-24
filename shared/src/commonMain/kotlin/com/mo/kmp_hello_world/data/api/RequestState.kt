package com.mo.kmp_hello_world.data.api

sealed class RequestState<T>(
    val error: String? = null,
    val data : T? =null
){
    class Loading<T> : RequestState<T>()
    class Error<T>(error: String?) : RequestState<T>( error = error)

    class Success<T>(data : T? =null) : RequestState<T>( data = data)
}
