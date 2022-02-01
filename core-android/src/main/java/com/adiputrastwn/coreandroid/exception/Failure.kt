package com.adiputrastwn.coreandroid.exception

sealed class Failure {
    object NetworkConnection : Failure()
    object RequestTimeout : Failure()
    data class ServerError(val message: String?) : Failure()
    data class RequestException(val t: Throwable) : Failure()
}