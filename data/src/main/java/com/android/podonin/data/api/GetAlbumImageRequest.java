package com.android.podonin.data.api;

import android.graphics.Bitmap;

import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

public interface GetAlbumImageRequest {

    @GET
    @Streaming
    Observable<Bitmap> getAlbumImage(@Url String url);
}
