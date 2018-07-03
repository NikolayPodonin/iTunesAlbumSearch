package com.android.podonin.itunesalbumsearch.mapping;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.android.podonin.domain.model.Album;
import com.android.podonin.domain.model.Song;
import com.android.podonin.itunesalbumsearch.model.AlbumParcel;
import com.android.podonin.itunesalbumsearch.model.SongParcel;

public class AlbumSongMapper {
    @NonNull
    public static AlbumParcel albumMap(@NonNull Album album) {
        int mAlbumId = album.getCollectionId();
        Bitmap mIcon60 = album.getArtwork60();
        Bitmap mIcon100 = album.getArtwork100();
        String mAlbumName = album.getCollectionName() != null ? album.getCollectionName() : "";
        String mArtistName = album.getArtistName() != null ? album.getArtistName() : "";
        String mPrice = album.getCollectionPrice() > 0 ? String.valueOf(album.getCollectionPrice()) : "0";
        String mCurrency = album.getCurrency() != null ? album.getCurrency() : "";
        String mReleaseDate = album.getReleaseDate() != null ? album.getReleaseDate().substring(0, 10) : "";
        String mCountry = album.getCountry() != null ? album.getCountry() : "";
        String mCopyright = album.getCopyright() != null ? album.getCopyright() : "";
        String mTrackCount = String.valueOf(album.getTrackCount());
        return new AlbumParcel(
                mAlbumId,
                mIcon60,
                mIcon100,
                mAlbumName,
                mArtistName,
                mPrice,
                mCurrency,
                mReleaseDate,
                mCountry,
                mCopyright,
                mTrackCount
        );
    }

    @NonNull
    public static SongParcel songMap(@NonNull Song song) {
        String mTrackName = song.getTrackName() != null ? song.getTrackName() : "";
        String mTrackNumber = String.valueOf(song.getTrackNumber());
        String mTrackTime = getTimeString(song.getTrackTimeMillis());
        return new SongParcel(mTrackName, mTrackNumber, mTrackTime);
    }

    @NonNull
    private static String getTimeString(int timeMillis) {
        int minutes = (timeMillis / 1000) / 60;
        String minStr = String.valueOf(minutes);
        int seconds = (timeMillis / 1000) % 60;
        String secStr = seconds < 10 ? "0" + String.valueOf(seconds) : String.valueOf(seconds);
        return minStr + ":" + secStr;
    }
}
