package com.android.podonin.itunesalbumsearch.view;

import android.support.annotation.NonNull;

import com.android.podonin.itunesalbumsearch.model.AlbumParcel;
import com.android.podonin.itunesalbumsearch.model.SongParcel;

import java.util.List;


public interface AlbumDetailsFragmentView extends LoadingView {
    void showAlbumDetails(@NonNull AlbumParcel album);
    void hideAlbumDetails();
    void showSongs(List<SongParcel> songs);
    void showNextSong(SongParcel song);
}
