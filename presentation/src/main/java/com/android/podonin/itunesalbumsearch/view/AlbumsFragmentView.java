package com.android.podonin.itunesalbumsearch.view;

import com.android.podonin.domain.model.Album;

public interface AlbumsFragmentView extends LoadingView {
    void showNextAlbum(Album album);
    void clearAlbums();
    void goToDetails(Integer albumId);
}
