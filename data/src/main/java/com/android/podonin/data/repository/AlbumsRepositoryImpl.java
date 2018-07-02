package com.android.podonin.data.repository;

import android.graphics.Bitmap;

import com.android.podonin.data.api.GetAlbumImageRequest;
import com.android.podonin.data.api.GetAlbumSongsRequest;
import com.android.podonin.data.api.GetAlbumsSearchRequest;
import com.android.podonin.data.mapping.EntityMapper;
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

public class AlbumsRepositoryImpl implements AlbumsRepository {
    private static final String ENDPOINT = "https://itunes.apple.com/";

    private static final Retrofit sRetrofit = new Retrofit.Builder()
            .baseUrl(ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .build();

    @Override
    public Observable<List<Album>> getAlbums(String term) {
        String encodeTerm = getEncodedString(term);
        GetAlbumsSearchRequest request = sRetrofit.create(GetAlbumsSearchRequest.class);
        return request
                .getAlbums(encodeTerm)
                .map(EntityMapper::transformAlbumsResponse);
    }

    @Override
    public Observable<List<ITunesObject>> getAlbumSongs(int albumId) {
        GetAlbumSongsRequest request = sRetrofit.create(GetAlbumSongsRequest.class);
        return request.getAlbumSongs(albumId)
                .map(EntityMapper::transformSongsResponse);
    }

    @Override
    public Observable<Bitmap> getAlbumIcon(String url) {
        String urlBody = url.replaceFirst("^https?://.+/", "");
        String urlHead = url.replace(urlBody, "");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlHead)
                .addConverterFactory(BitmapConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        GetAlbumImageRequest request = retrofit.create(GetAlbumImageRequest.class);
        return request.getAlbumImage(urlBody);
    }

    private String getEncodedString(String term) {
        String encodeTerm;
        try {
            encodeTerm = URLEncoder.encode(term, "UTF-16");
        } catch (UnsupportedEncodingException e) {
            encodeTerm = term.trim().replaceAll(" +", "+").toLowerCase();
        }
        return encodeTerm;
    }
}
