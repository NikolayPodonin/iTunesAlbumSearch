package com.android.podonin.itunesalbumsearch.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.android.podonin.domain.interactor.UseCase;
import com.android.podonin.domain.interactor.albuminteractor.AlbumDetailsUseCase;
import com.android.podonin.domain.model.Album;
import com.android.podonin.domain.model.ITunesObject;
import com.android.podonin.domain.model.Song;
import com.android.podonin.itunesalbumsearch.mapping.AlbumSongMapper;
import com.android.podonin.itunesalbumsearch.model.AlbumParcel;
import com.android.podonin.itunesalbumsearch.model.SongParcel;
import com.android.podonin.itunesalbumsearch.rx.ITunesObjectSubscriber;
import com.android.podonin.itunesalbumsearch.view.AlbumDetailsFragmentView;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.HttpException;

/**
 * Providing albumId to interactor, handling interactor response and errors.
 */
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

    public void dispatchCreate(AlbumParcel albumParcel, List<SongParcel> songParcels) {
        mFragmentView.hideNoItemsText();
        mFragmentView.hideLoadingProcess();
        mFragmentView.showAlbumDetails(albumParcel);
        mFragmentView.showSongs(songParcels);
    }

    public void dispatchDestroy() {
        mUseCase.unsubscribe();
    }

    private void onGetAlbum(@NonNull AlbumParcel album) {
        mFragmentView.showAlbumDetails(album);
        mFragmentView.hideLoadingProcess();
    }

    private void onMistake() {
        mFragmentView.hideAlbumDetails();
        mFragmentView.showNoItemsText();
    }

    private class AlbumDetailsSubscriber extends ITunesObjectSubscriber {

        @Override
        public void onNextSong(@NotNull Song song) {
            SongParcel songParcel = AlbumSongMapper.songMap(song);
            mFragmentView.showNextSong(songParcel);
        }

        @Override
        public void onNextAlbum(@NotNull Album album) {
            AlbumParcel albumParcel = AlbumSongMapper.albumMap(album);
            onGetAlbum(albumParcel);
        }

        @Override
        public void onUnexpectedError(@Nullable Throwable e) {
            mFragmentView.showUnexpectedError();
            onMistake();

        }

        @Override
        public void onNetworkError(@NotNull IOException e) {
            mFragmentView.showConnectionError();
            onMistake();
        }

        @Override
        public void onHttpError(@NotNull HttpException e) {
            mFragmentView.showHttpError();
            onMistake();
        }

        @Override
        public void onCompleted() {
        }
    }
}
