package com.android.podonin.itunesalbumsearch.view;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;

import com.android.podonin.domain.model.Song;


public interface AlbumDetailsFragmentView extends LoadingView {
    void showAlbumDetails(@Nullable Bitmap icon, @Nullable String albumName, @Nullable String artistName,
                          @Nullable String price, @Nullable String currency, @Nullable String releaseDate,
                          @Nullable String country, @Nullable String copyright);
    void hideAlbumDetails();

    void showNextSong(Song song);
}
