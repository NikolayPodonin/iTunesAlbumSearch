package com.android.podonin.itunesalbumsearch.rx

import com.android.podonin.domain.model.Album
import com.android.podonin.domain.model.ITunesObject
import com.android.podonin.domain.model.Song
import java.lang.IllegalArgumentException

/**
 * Branching onNext to specify type of object.
 */
abstract class ITunesObjectSubscriber: ErrorSubscriber<ITunesObject>() {
    override fun onNext(t: ITunesObject) {
        when(t) {
            is Album -> onNextAlbum(t)
            is Song -> onNextSong(t)
            else -> onUnexpectedError(IllegalArgumentException("Unknown type response"))
        }
    }

    abstract fun onNextSong(t: Song)

    abstract fun onNextAlbum(t: Album)
}