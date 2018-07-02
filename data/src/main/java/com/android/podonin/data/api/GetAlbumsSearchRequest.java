package com.android.podonin.data.api;

import com.android.podonin.data.entity.SearchRequestResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface GetAlbumsSearchRequest {
    String BASE_URL = "search?entity=album";
    String TERM = "term";

    @GET(BASE_URL)
    Observable<SearchRequestResult> getAlbums(@Query(TERM) String term);
}
