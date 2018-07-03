package com.android.podonin.data.mapping;

import android.support.annotation.NonNull;

import com.android.podonin.data.entity.Result;
import com.android.podonin.data.entity.SearchRequestResult;
import com.android.podonin.domain.model.Album;
import com.android.podonin.domain.model.ITunesObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper from data layer entities to domain layer models.
 */
public class EntityMapper {
    private static final String TYPE_COLLECTION = "collection";
    private static final String TYPE_TRACK = "track";

    private static final ResultToAlbum sResultToAlbum = ResultToAlbum.Instance;
    private static final ResultToSong sResultToSong = ResultToSong.Instance;

    @NonNull
    public static List<Album> transformAlbumsResponse(SearchRequestResult requestResult) {
        List<Album> albums = new ArrayList<>();
        if (requestResult == null) {
            return albums;
        }
        for (Result result : requestResult.getResults()) {
            albums.add(sResultToAlbum.getAlbum(result));
        }
        return albums;
    }

    @NonNull
    public static List<ITunesObject> transformSongsResponse(SearchRequestResult requestResult) {
        List<ITunesObject> resultList = new ArrayList<>();
        if (requestResult == null) {
            return resultList;
        }
        for (Result res : requestResult.getResults()) {
            resultList.add(transform(res));
        }
        return resultList;
    }

    @NonNull
    public static ITunesObject transform(Result result) {
        switch (result.getWrapperType()) {
            case TYPE_COLLECTION:
                return sResultToAlbum.getAlbum(result);
            case TYPE_TRACK:
                return sResultToSong.getSong(result);
            default:
                return new ITunesObject();
        }
    }
}
