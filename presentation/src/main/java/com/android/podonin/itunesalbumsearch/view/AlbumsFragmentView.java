package com.android.podonin.itunesalbumsearch.view;

import com.android.podonin.itunesalbumsearch.model.AlbumParcel;

import java.util.List;

public interface AlbumsFragmentView extends LoadingView {
    void showAlbums(List<AlbumParcel> albums);
    void showNextAlbum(AlbumParcel album);
    void clearAlbums();
    void goToDetails(int albumId);
}
