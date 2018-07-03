package com.android.podonin.domain.model;

/**
 * Song class (redundant fields are for future).
 */
public class Song extends ITunesObject {
    private String mKind;
    private int mTrackId;
    private String mTrackName;
    private String mTrackCensoredName;
    private String mTrackViewUrl;
    private String mPreviewUrl;
    private String mArtworkUrl30;
    private double mTrackPrice;
    private String mTrackExplicitness;
    private int mDiscCount;
    private int mDiscNumber;
    private int mTrackNumber;
    private int mTrackTimeMillis;

    public String getKind() {
        return mKind;
    }

    public void setKind(String kind) {
        mKind = kind;
    }

    public int getTrackId() {
        return mTrackId;
    }

    public void setTrackId(int trackId) {
        mTrackId = trackId;
    }

    public String getTrackName() {
        return mTrackName;
    }

    public void setTrackName(String trackName) {
        mTrackName = trackName;
    }

    public String getTrackCensoredName() {
        return mTrackCensoredName;
    }

    public void setTrackCensoredName(String trackCensoredName) {
        mTrackCensoredName = trackCensoredName;
    }

    public String getTrackViewUrl() {
        return mTrackViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl) {
        mTrackViewUrl = trackViewUrl;
    }

    public String getPreviewUrl() {
        return mPreviewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        mPreviewUrl = previewUrl;
    }

    public String getArtworkUrl30() {
        return mArtworkUrl30;
    }

    public void setArtworkUrl30(String artworkUrl30) {
        mArtworkUrl30 = artworkUrl30;
    }

    public double getTrackPrice() {
        return mTrackPrice;
    }

    public void setTrackPrice(double trackPrice) {
        mTrackPrice = trackPrice;
    }

    public String getTrackExplicitness() {
        return mTrackExplicitness;
    }

    public void setTrackExplicitness(String trackExplicitness) {
        mTrackExplicitness = trackExplicitness;
    }

    public int getDiscCount() {
        return mDiscCount;
    }

    public void setDiscCount(int discCount) {
        mDiscCount = discCount;
    }

    public int getDiscNumber() {
        return mDiscNumber;
    }

    public void setDiscNumber(int discNumber) {
        mDiscNumber = discNumber;
    }

    public int getTrackNumber() {
        return mTrackNumber;
    }

    public void setTrackNumber(int trackNumber) {
        mTrackNumber = trackNumber;
    }

    public int getTrackTimeMillis() {
        return mTrackTimeMillis;
    }

    public void setTrackTimeMillis(int trackTimeMillis) {
        mTrackTimeMillis = trackTimeMillis;
    }

}
