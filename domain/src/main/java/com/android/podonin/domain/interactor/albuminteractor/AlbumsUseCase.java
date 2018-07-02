package com.android.podonin.domain.interactor.albuminteractor;

import com.android.podonin.domain.interactor.UseCase;
import com.android.podonin.domain.model.Album;
import com.android.podonin.domain.repository.AlbumsRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.functions.Func1;

public class AlbumsUseCase extends UseCase<Album, AlbumsUseCase.Params> {
    private AlbumsRepository mAlbumsRepository;

    public AlbumsUseCase(AlbumsRepository albumsRepository, Scheduler executionThread, Scheduler postExecutionThread) {
        super(executionThread, postExecutionThread);
        mAlbumsRepository = albumsRepository;
    }

    @Override
    protected Observable<Album> buildUseCaseObservable(Params params) {
        return mAlbumsRepository.getAlbums(params.query)
                .map(this::sort)
                .flatMap((Func1<List<Album>, Observable<Album>>)
                        albumList -> (Observable<Album>) Observable.from(albumList))
                .map(this::albumIconDownload);
    }

    private List<Album> sort(List<Album> albums) {
        Collections.sort(albums, (o1, o2) -> {
            String o1CollectionName = o1.getCollectionName();
            String o2CollectionName = o2.getCollectionName();
            if (o1CollectionName == null || o2CollectionName == null) {
                return 0;
            }
            return o1CollectionName.compareTo(o2CollectionName);
        });
        return albums;
    }

    private Album albumIconDownload(Album album) {
        String url = album.getArtworkUrl60();
        mAlbumsRepository.getAlbumIcon(url)
                .subscribe(album::setArtwork60);
        return album;
    }

    public static class Params {
        final String query;

        public Params(String query) {
            this.query = query;
        }
    }
}
