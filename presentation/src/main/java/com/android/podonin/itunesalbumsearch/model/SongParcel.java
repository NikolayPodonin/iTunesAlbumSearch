package com.android.podonin.itunesalbumsearch.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Parcelable and optimized Song for comfortable present and save.
 */
public class SongParcel implements Parcelable {
    public SongParcel(String trackName, String trackNumber, String trackTime) {
        mTrackName = trackName;
        mTrackNumber = trackNumber;
        mTrackTime = trackTime;
    }

    protected SongParcel(Parcel in) {
        String[] strings = new String[3];
        in.writeStringArray(strings);
        mTrackName = strings[0];
        mTrackNumber = strings[1];
        mTrackTime = strings[2];
    }

    public static final Creator<SongParcel> CREATOR = new Creator<SongParcel>() {
        @Override
        public SongParcel createFromParcel(Parcel in) {
            return new SongParcel(in);
        }

        @Override
        public SongParcel[] newArray(int size) {
            return new SongParcel[size];
        }
    };

    private String mTrackName;
    private String mTrackNumber;
    private String mTrackTime;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] { mTrackName, mTrackNumber, mTrackTime });
    }

    public String getTrackName() {
        return mTrackName;
    }

    public void setTrackName(String trackName) {
        mTrackName = trackName;
    }

    public String getTrackNumber() {
        return mTrackNumber;
    }

    public void setTrackNumber(String trackNumber) {
        mTrackNumber = trackNumber;
    }

    public String getTrackTime() {
        return mTrackTime;
    }

    public void setTrackTime(String trackTime) {
        mTrackTime = trackTime;
    }
}
