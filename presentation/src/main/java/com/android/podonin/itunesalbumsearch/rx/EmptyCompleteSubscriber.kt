package com.android.podonin.itunesalbumsearch.rx

/**
 * Branching onCompleted to show were or not objects observed.
 */
abstract class EmptyCompleteSubscriber<T>: ErrorSubscriber<T>() {
    private var isEmpty = true
    override fun onCompleted() {
        when (isEmpty) {
            true -> onCompletedEmpty()
            false -> onCompletedNotEmpty()
        }
    }

    override fun onNext(t: T) {
        isEmpty = false
    }

    abstract fun onCompletedNotEmpty()

    abstract fun onCompletedEmpty()
}