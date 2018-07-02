package com.android.podonin.itunesalbumsearch.rx

import retrofit2.HttpException
import rx.Subscriber
import java.io.IOException

abstract class ErrorSubscriber<T>: Subscriber<T>() {
    override fun onError(e: Throwable?) {
        when(e) {
            is HttpException -> onHttpError(e)
            is IOException -> onNetworkError(e)
            else -> onUnexpectedError(e)
        }
    }

    abstract fun onUnexpectedError(e: Throwable?)

    abstract fun onNetworkError(e: IOException)

    abstract fun onHttpError(e: HttpException)
}