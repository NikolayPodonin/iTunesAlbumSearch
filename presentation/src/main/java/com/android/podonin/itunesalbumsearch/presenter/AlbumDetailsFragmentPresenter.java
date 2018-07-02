package com.android.podonin.itunesalbumsearch.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.android.podonin.domain.interactor.UseCase;
import com.android.podonin.domain.interactor.albuminteractor.AlbumDetailsUseCase;
import com.android.podonin.domain.model.Album;
import com.android.podonin.domain.model.ITunesObject;
import com.android.podonin.domain.model.Song;
import com.android.podonin.itunesalbumsearch.rx.ITunesObjectSubscriber;
import com.android.podonin.itunesalbumsearch.view.AlbumDetailsFragmentView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

import retrofit2.HttpException;

public class AlbumDetailsFragmentPresenter {
    private AlbumDetailsFragmentView mFragmentView;
    private UseCase<ITunesObject, AlbumDetailsUseCase.Params> mUseCase;

    public AlbumDetailsFragmentPresenter(@NonNull AlbumDetailsFragmentView fragmentView,
                                         @NonNull UseCase<ITunesObject, AlbumDetailsUseCase.Params> useCase) {
        mFragmentView = fragmentView;
        mUseCase = useCase;
    }


    public void dispatchCreate(int albumId) {
        mFragmentView.hideNoItemsText();
        mFragmentView.hideAlbumDetails();
        mFragmentView.showLoadingProcess();
        mUseCase.execute(new AlbumDetailsSubscriber(), new AlbumDetailsUseCase.Params(albumId));
    }

    public void dispatchDestroy() {
        mUseCase.unsubscribe();
    }

    private void onGetAlbum(@NonNull Album album) {
        mFragmentView.showAlbumDetails(
                album.getArtwork100(),
                album.getCollectionName(),
                album.getArtistName(),
                String.valueOf(album.getCollectionPrice()),
                album.getCurrency(),
                album.getReleaseDate() != null ?
                        album.getReleaseDate().substring(0, 10) : null,
                album.getCountry(),
                album.getCopyright()
        );
        mFragmentView.hideLoadingProcess();
    }

    private void onMistake() {
        mFragmentView.hideAlbumDetails();
        mFragmentView.hideNoItemsText();
    }

    private class AlbumDetailsSubscriber extends ITunesObjectSubscriber {

        @Override
        public void onNextSong(@NotNull Song t) {
            Log.i("HUMANS DESTROY ", "onNextSong: " + t.getTrackName());
            mFragmentView.showNextSong(t);
        }

        @Override
        public void onNextAlbum(@NotNull Album t) {
            Log.i("HUMANS DESTROY", "onNextAlbum: ");
            onGetAlbum(t);
        }

        @Override
        public void onUnexpectedError(@Nullable Throwable e) {
            Log.i("HUMANS DESTROY", "onUnexpectedError: " + (e != null ? e.getMessage() : ""));
            onMistake();
        }

        @Override
        public void onNetworkError(@NotNull IOException e) {
            Log.i("HUMANS DESTROY", "onNetworkError: " + e.getMessage());
            onMistake();
        }

        @Override
        public void onHttpError(@NotNull HttpException e) {
            Log.i("HUMANS DESTROY", "onHttpError: " + e.message());
            onMistake();
        }

        @Override
        public void onCompleted() {
            Log.i("HUMANS DESTROY", "onCompleted: ");
        }
    }
}
