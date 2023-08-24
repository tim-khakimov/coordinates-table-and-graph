package com.timkhakimov.coordinatestableandgraph.data.model

import retrofit2.HttpException
import java.io.IOException

sealed interface Response<T> {
    data class Success<T>(val data: T) : Response<T>
    sealed class Error<T>() : Response<T> {
        class Unknown<T> : Error<T>()
        class Network<T> : Error<T>()
        class Server<T>(val message: String?) : Error<T>()
    }
}

inline fun <reified T> tryCatching(function: () -> T): Response<T> {
    return try {
        Response.Success(function.invoke())
    } catch (e: Throwable) {
        when (e) {
            is IOException -> Response.Error.Network()
            is HttpException -> Response.Error.Server(e.response()?.errorBody()?.string())
            else -> Response.Error.Unknown()
        }
    }
}

inline fun <reified T> Response<T>.onSuccess(function: (T) -> Unit): Response<T> {
    if (this is Response.Success) {
        function.invoke(data)
    }
    return this
}


inline fun <reified T> Response<T>.onError(function: (Response.Error<T>) -> Unit): Response<T> {
    if (this is Response.Error) {
        function.invoke(this)
    }
    return this
}

inline fun <reified T> Response<T>.onFinish(function: () -> Unit): Response<T> {
    function.invoke()
    return this
}