package com.adiputrastwn.baseandroid.data.datasource.remote.helper

import com.adiputrastwn.coreandroid.exception.Failure
import com.adiputrastwn.coreandroid.functional.Either
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend fun <T> safeNetworkCall(retrofitCall: (suspend () -> Response<T>)): Either<Failure, T> {
    return try {
        val response = retrofitCall.invoke()
        val body = response.body()
        if (response.isSuccessful && body != null) {
            println("SUCCESS CALL")
            return Either.Right(body)
        } else {
            println("ERROR RESPONSE CALL")
            if (response.errorBody()?.toString()?.contains("gateway time-out error") == true)
                Either.Left(Failure.RequestTimeout)
            else {
                // parsing errorResponseBody when Error json format provided
                Either.Left(Failure.ServerError("Error Server"))
            }
        }
    } catch (e: UnknownHostException) {
        println("ERROR NO INET CALL")
        Either.Left(Failure.NetworkConnection)
    } catch (e: Throwable) {
        e.printStackTrace()
        println("ERROR THROW CALL: ${e.localizedMessage}")
        Either.Left(Failure.RequestException(e))
    } catch (se3: SocketTimeoutException) {
        println("ERROR TIMEOUT CALL")
        Either.Left(Failure.RequestTimeout)
    }
}