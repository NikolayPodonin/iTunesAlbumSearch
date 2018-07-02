package com.android.podonin.data.api;

import com.android.podonin.data.entity.SearchRequestResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface GetAlbumSongsRequest {
    String BASE_URL = "lookup?entity=song";
    String ID = "id";

    @GET(BASE_URL)
    Observable<SearchRequestResult> getAlbumSongs(@Query(ID) int albumId);
}
