package com.android.podonin.domain.repository;

import android.graphics.Bitmap;

import com.android.podonin.domain.model.Album;
import com.android.podonin.domain.model.ITunesObject;

import java.util.List;

import rx.Observable;

public interface AlbumsRepository {
    Observable<List<Album>> getAlbums(String term);
    Observable<List<ITunesObject>> getAlbumSongs(int albumId);
    Observable<Bitmap> getAlbumIcon(String url);
}
