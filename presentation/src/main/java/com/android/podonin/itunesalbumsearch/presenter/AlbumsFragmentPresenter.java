package com.android.podonin.itunesalbumsearch.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.android.podonin.domain.interactor.UseCase;
import com.android.podonin.domain.interactor.albuminteractor.AlbumsUseCase;
import com.android.podonin.domain.model.Album;
import com.android.podonin.itunesalbumsearch.rx.EmptyCompleteSubscriber;
import com.android.podonin.itunesalbumsearch.rx.ErrorSubscriber;
import com.android.podonin.itunesalbumsearch.view.AlbumsFragmentView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import retrofit2.HttpException;

public class AlbumsFragmentPresenter {
    private AlbumsFragmentView mFragmentView;
    private UseCase<Album, AlbumsUseCase.Params> mAlbumsUseCase;

    public AlbumsFragmentPresenter(AlbumsFragmentView fragmentView, UseCase<Album, AlbumsUseCase.Params> albumsUseCase) {
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

    public void onAlbumChoose(@NonNull Integer albumId) {
        mFragmentView.goToDetails(albumId);
    }

    public void onSearch(String query) {
        mFragmentView.hideNoItemsText();
        mFragmentView.clearAlbums();
        mFragmentView.showLoadingProcess();
        mAlbumsUseCase.execute(new AlbumSubscriber(), new AlbumsUseCase.Params(query));
    }

    private class AlbumSubscriber extends EmptyCompleteSubscriber<Album> {

        @Override
        public void onCompleted() {
            super.onCompleted();
            Log.i("DESTROY HUMANS", "onCompleted: ");
            mFragmentView.hideLoadingProcess();
        }

        @Override
        public void onNext(Album album) {
            super.onNext(album);
            Log.i("DESTROY HUMANS", "onNext: " + album.getCollectionName());
            mFragmentView.hideLoadingProcess();
            mFragmentView.showNextAlbum(album);
        }

        @Override
        public void onUnexpectedError(@Nullable Throwable e) {
            Log.i("DESTROY HUMANS", "onUnexpectedError: " + (e != null ? e.getMessage() : ""));
            mFragmentView.hideLoadingProcess();

        }

        @Override
        public void onNetworkError(@NotNull IOException e) {
            Log.i("DESTROY HUMANS", "onNetworkError: " + e.getMessage());
            mFragmentView.hideLoadingProcess();

        }

        @Override
        public void onHttpError(@NotNull HttpException e) {
            Log.i("DESTROY HUMANS", "onHttpError: " + e.message());
            mFragmentView.hideLoadingProcess();

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
