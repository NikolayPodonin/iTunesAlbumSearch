package com.android.podonin.data.repository;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.android.podonin.data.api.GetAlbumImageRequest;
import com.android.podonin.data.api.GetAlbumSongsRequest;
import com.android.podonin.data.api.GetAlbumsSearchRequest;
import com.android.podonin.data.entity.Result;
import com.android.podonin.data.mapping.EntityMapper;
import com.android.podonin.data.retrofit.BitmapConverterFactory;
import com.android.podonin.domain.model.Album;
import com.android.podonin.domain.model.ITunesObject;
import com.android.podonin.domain.repository.AlbumsRepository;
import com.google.gson.GsonBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Represent layer between domain and iTunes server.
 */
public class AlbumsRepositoryImpl implements AlbumsRepository {
    @Override
    public Observable<List<Album>> getAlbums(String term) {
        Retrofit retrofit = getRetrofit(GetAlbumsSearchRequest.ENDPOINT,
                GsonConverterFactory.create(new GsonBuilder().create()));
        String encodeTerm = getEncodedString(term);
        GetAlbumsSearchRequest request = retrofit.create(GetAlbumsSearchRequest.class);
        return request
                .getAlbums(encodeTerm)
                .map(EntityMapper::transformAlbumsResponse);
    }

    @Override
    public Observable<List<ITunesObject>> getAlbumSongs(int albumId) {
        Retrofit retrofit = getRetrofit(GetAlbumSongsRequest.ENDPOINT,
                GsonConverterFactory.create(new GsonBuilder().create()));
        GetAlbumSongsRequest request = retrofit.create(GetAlbumSongsRequest.class);
        return request.getAlbumSongs(albumId)
                .map(EntityMapper::transformSongsResponse);
    }

    @Override
    public Observable<Bitmap> getAlbumIcon(String url) {
        String urlBody = url.replaceFirst("^https?://[^/]+/", "");
        String urlHead = url.replace(urlBody, "");
        Retrofit retrofit = getRetrofit(urlHead, BitmapConverterFactory.create());
        GetAlbumImageRequest request = retrofit.create(GetAlbumImageRequest.class);
        return request.getAlbumImage(urlBody);
    }

    @NonNull
    private Retrofit getRetrofit(@NonNull String urlHead, @NonNull Converter.Factory converterFactory) {
        return new Retrofit.Builder()
                .baseUrl(urlHead)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @NonNull
    private String getEncodedString(@NonNull String term) {
        String encodeTerm;
        try {
            encodeTerm = URLEncoder.encode(term, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            encodeTerm = term.trim().replaceAll(" +", "+").toLowerCase();
        }
        return encodeTerm;
    }
}
