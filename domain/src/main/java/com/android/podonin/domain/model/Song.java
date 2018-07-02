package com.android.podonin.domain.model;

public class Song extends ITunesObject {
    private String mKind;
    private Integer mTrackId;
    private String mTrackName;
    private String mTrackCensoredName;
    private String mTrackViewUrl;
    private String mPreviewUrl;
    private String mArtworkUrl30;
    private Double mTrackPrice;
    private String mTrackExplicitness;
    private Integer mDiscCount;
    private Integer mDiscNumber;
    private Integer mTrackNumber;
    private Integer mTrackTimeMillis;
    private Boolean mIsStreamable;

    public String getKind() {
        return mKind;
    }

    public void setKind(String kind) {
        mKind = kind;
    }

    public Integer getTrackId() {
        return mTrackId;
    }

    public void setTrackId(Integer trackId) {
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

    public Double getTrackPrice() {
        return mTrackPrice;
    }

    public void setTrackPrice(Double trackPrice) {
        mTrackPrice = trackPrice;
    }

    public String getTrackExplicitness() {
        return mTrackExplicitness;
    }

    public void setTrackExplicitness(String trackExplicitness) {
        mTrackExplicitness = trackExplicitness;
    }

    public Integer getDiscCount() {
        return mDiscCount;
    }

    public void setDiscCount(Integer discCount) {
        mDiscCount = discCount;
    }

    public Integer getDiscNumber() {
        return mDiscNumber;
    }

    public void setDiscNumber(Integer discNumber) {
        mDiscNumber = discNumber;
    }

    public Integer getTrackNumber() {
        return mTrackNumber;
    }

    public void setTrackNumber(Integer trackNumber) {
        mTrackNumber = trackNumber;
    }

    public Integer getTrackTimeMillis() {
        return mTrackTimeMillis;
    }

    public void setTrackTimeMillis(Integer trackTimeMillis) {
        mTrackTimeMillis = trackTimeMillis;
    }

    public Boolean getStreamable() {
        return mIsStreamable;
    }

    public void setStreamable(Boolean streamable) {
        mIsStreamable = streamable;
    }
}
