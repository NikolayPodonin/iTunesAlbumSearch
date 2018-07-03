package com.android.podonin.itunesalbumsearch.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.android.podonin.domain.interactor.UseCase;
import com.android.podonin.domain.interactor.albuminteractor.AlbumsUseCase;
import com.android.podonin.domain.model.Album;
import com.android.podonin.itunesalbumsearch.mapping.AlbumSongMapper;
import com.android.podonin.itunesalbumsearch.model.AlbumParcel;
import com.android.podonin.itunesalbumsearch.rx.EmptyCompleteSubscriber;
import com.android.podonin.itunesalbumsearch.rx.ErrorSubscriber;
import com.android.podonin.itunesalbumsearch.view.AlbumsFragmentView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.HttpException;

/**
 * Handling user acts, providing user queries to interactor, handling interactor responses and errors.
 */
public class AlbumsFragmentPresenter {
    private AlbumsFragmentView mFragmentView;
    private UseCase<Album, AlbumsUseCase.Params> mAlbumsUseCase;
    private List<AlbumParcel> mAlbumParcels = new ArrayList<>();

    public AlbumsFragmentPresenter(@NonNull AlbumsFragmentView fragmentView,
                                   @NonNull UseCase<Album, AlbumsUseCase.Params> albumsUseCase) {
        mFragmentView = fragmentView;
        mAlbumsUseCase = albumsUseCase;
    }

    public void dispatchCreate() {
        mFragmentView.showNoItemsText();
        mFragmentView.hideLoadingProcess();
    }

    public void dispatchDestroy() {
        mAlbumsUseCase.unsubscribe();
    }

    public void onSavedAlbumsRestore(List<AlbumParcel> albumParcels) {
        if (albumParcels.size() != 0) {
            mFragmentView.hideNoItemsText();
            mFragmentView.showAlbums(albumParcels);
        }
    }

    public void onAlbumChoose(int albumId) {
        mFragmentView.goToDetails(albumId);
    }

    public void onSearch(@NonNull String query) {
        mAlbumsUseCase.unsubscribe();
        mFragmentView.hideNoItemsText();
        mFragmentView.clearAlbums();
        mFragmentView.showLoadingProcess();
        mAlbumsUseCase.execute(new AlbumSubscriber(), new AlbumsUseCase.Params(query));
    }

    private class AlbumSubscriber extends EmptyCompleteSubscriber<Album> {

        @Override
        public void onCompleted() {
            super.onCompleted();
            mFragmentView.hideLoadingProcess();
        }

        @Override
        public void onNext(@NonNull Album album) {
            super.onNext(album);
            mFragmentView.hideLoadingProcess();
            AlbumParcel albumParcel = AlbumSongMapper.albumMap(album);
            mAlbumParcels.add(albumParcel);
            mFragmentView.showNextAlbum(albumParcel);
        }

        @Override
        public void onUnexpectedError(@Nullable Throwable e) {
            mFragmentView.hideLoadingProcess();
            mFragmentView.showUnexpectedError();

        }

        @Override
        public void onNetworkError(@NotNull IOException e) {
            mFragmentView.hideLoadingProcess();
            mFragmentView.showConnectionError();
        }

        @Override
        public void onHttpError(@NotNull HttpException e) {
            mFragmentView.hideLoadingProcess();
            mFragmentView.showHttpError();
        }

        @Override
        public void onCompletedNotEmpty() {

        }

        @Override
        public void onCompletedEmpty() {
            mFragmentView.showNoItemsText();
        }
    }
}
