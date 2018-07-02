package com.android.podonin.domain.interactor.albuminteractor;

import com.android.podonin.domain.interactor.UseCase;
import com.android.podonin.domain.model.Album;
import com.android.podonin.domain.model.ITunesObject;
import com.android.podonin.domain.repository.AlbumsRepository;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

public class AlbumDetailsUseCase extends UseCase<ITunesObject, AlbumDetailsUseCase.Params> {
    private final AlbumsRepository mRepository;

    public AlbumDetailsUseCase(AlbumsRepository repository, Scheduler executionThread, Scheduler postExecutionThread) {
        super(executionThread, postExecutionThread);
        mRepository = repository;
    }

    @Override
    protected Observable<ITunesObject> buildUseCaseObservable(Params params) {
        return mRepository.getAlbumSongs(params.mAlbumId)
                .flatMap((Func1<List<ITunesObject>, Observable<ITunesObject>>) Observable::from)
                .map(this::albumIconDownload);
    }

    private ITunesObject albumIconDownload(ITunesObject iTunesObject) {
        if (iTunesObject instanceof Album) {
            Album album = (Album)iTunesObject;
            String url = album.getArtworkUrl100();
            mRepository.getAlbumIcon(url)
                    .subscribe(album::setArtwork100);
            return album;
        }
        return iTunesObject;
    }

    public static class Params {
        final int mAlbumId;

        public Params(int albumId) {
            mAlbumId = albumId;
        }
    }
}
