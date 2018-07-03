package com.android.podonin.itunesalbumsearch.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Parcelable and optimized Album for comfortable present and save.
 */
public class AlbumParcel implements Parcelable {
    public AlbumParcel(int albumId, Bitmap icon60, Bitmap icon100, String albumName, String artistName,
                       String price, String currency, String releaseDate,
                       String country, String copyright, String trackCount) {
        mAlbumId = albumId;
        mIcon60 = icon60;
        mIcon100 = icon100;
        mAlbumName = albumName;
        mArtistName = artistName;
        mPrice = price;
        mCurrency = currency;
        mReleaseDate = releaseDate;
        mCountry = country;
        mCopyright = copyright;
        mTrackCount = trackCount;
    }

    protected AlbumParcel(Parcel in) {
        mAlbumId = in.readInt();
        Bitmap[] bitmaps = (Bitmap[]) in.readParcelableArray(Bitmap.class.getClassLoader());
        mIcon60 = bitmaps[0];
        mIcon100 = bitmaps[1];
        String[] strings = new String[7];
        in.readStringArray(strings);
        mAlbumName = strings[0];
        mArtistName = strings[1];
        mPrice = strings[2];
        mCurrency = strings[3];
        mReleaseDate = strings[4];
        mCountry = strings[5];
        mCopyright = strings[6];

    }

    public static final Creator<AlbumParcel> CREATOR = new Creator<AlbumParcel>() {
        @Override
        public AlbumParcel createFromParcel(Parcel in) {
            return new AlbumParcel(in);
        }

        @Override
        public AlbumParcel[] newArray(int size) {
            return new AlbumParcel[size];
        }
    };

    private int mAlbumId;
    private Bitmap mIcon60;
    private Bitmap mIcon100;
    private String mAlbumName;
    private String mArtistName;
    private String mPrice;
    private String mCurrency;
    private String mReleaseDate;
    private String mCountry;
    private String mCopyright;
    private String mTrackCount;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mAlbumId);
        dest.writeStringArray(new String[] {
                mAlbumName,
                mArtistName,
                mPrice,
                mCurrency,
                mReleaseDate,
                mCountry,
                mCopyright,
                mTrackCount
        });
        dest.writeParcelableArray(new Bitmap[] { mIcon60, mIcon100 }, flags);
    }

    public int getAlbumId() {
        return mAlbumId;
    }

    public void setAlbumId(int albumId) {
        mAlbumId = albumId;
    }

    public Bitmap getIcon60() {
        return mIcon60;
    }

    public void setIcon60(Bitmap icon60) {
        mIcon60 = icon60;
    }

    public Bitmap getIcon100() {
        return mIcon100;
    }

    public void setIcon100(Bitmap icon100) {
        mIcon100 = icon100;
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public void setAlbumName(String albumName) {
        mAlbumName = albumName;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public void setArtistName(String artistName) {
        mArtistName = artistName;
    }

    public String getPrice() {
        return mPrice;
    }

    public void setPrice(String price) {
        mPrice = price;
    }

    public String getCurrency() {
        return mCurrency;
    }

    public void setCurrency(String currency) {
        mCurrency = currency;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getCopyright() {
        return mCopyright;
    }

    public void setCopyright(String copyright) {
        mCopyright = copyright;
    }

    public String getTrackCount() {
        return mTrackCount;
    }

    public void setTrackCount(String trackCount) {
        mTrackCount = trackCount;
    }
}
