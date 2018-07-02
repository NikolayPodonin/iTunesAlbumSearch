package com.android.podonin.domain.model;

import android.graphics.Bitmap;

public class Album extends ITunesObject {
    private String mWrapperType;
    private String mCollectionType;
    private Integer mArtistId;
    private Integer mCollectionId;
    private String mArtistName;
    private String mCollectionName;
    private String mCollectionCensoredName;
    private String mArtistViewUrl;
    private String mCollectionViewUrl;
    private String mArtworkUrl60;
    private Bitmap mArtwork60;
    private String mArtworkUrl100;
    private Bitmap mArtwork100;
    private Double mCollectionPrice;
    private String mCollectionExplicitness;
    private Integer mTrackCount;
    private String mCopyright;
    private String mCountry;
    private String mCurrency;
    private String mReleaseDate;
    private String mPrimaryGenreName;
    private Integer mAmgArtistId;
    private String mContentAdvisoryRating;


    public String getWrapperType() {
        return mWrapperType;
    }

    public void setWrapperType(String wrapperType) {
        mWrapperType = wrapperType;
    }

    public String getCollectionType() {
        return mCollectionType;
    }

    public void setCollectionType(String collectionType) {
        mCollectionType = collectionType;
    }

    public Integer getArtistId() {
        return mArtistId;
    }

    public void setArtistId(Integer artistId) {
        mArtistId = artistId;
    }

    public Integer getCollectionId() {
        return mCollectionId;
    }

    public void setCollectionId(Integer collectionId) {
        mCollectionId = collectionId;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public void setArtistName(String artistName) {
        mArtistName = artistName;
    }

    public String getCollectionName() {
        return mCollectionName;
    }

    public void setCollectionName(String collectionName) {
        mCollectionName = collectionName;
    }

    public String getCollectionCensoredName() {
        return mCollectionCensoredName;
    }

    public void setCollectionCensoredName(String collectionCensoredName) {
        mCollectionCensoredName = collectionCensoredName;
    }

    public String getArtistViewUrl() {
        return mArtistViewUrl;
    }

    public void setArtistViewUrl(String artistViewUrl) {
        mArtistViewUrl = artistViewUrl;
    }

    public String getCollectionViewUrl() {
        return mCollectionViewUrl;
    }

    public void setCollectionViewUrl(String collectionViewUrl) {
        mCollectionViewUrl = collectionViewUrl;
    }

    public String getArtworkUrl60() {
        return mArtworkUrl60;
    }

    public void setArtworkUrl60(String artworkUrl60) {
        mArtworkUrl60 = artworkUrl60;
    }

    public Bitmap getArtwork60() {
        return mArtwork60;
    }

    public void setArtwork60(Bitmap artwork60) {
        mArtwork60 = artwork60;
    }

    public String getArtworkUrl100() {
        return mArtworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        mArtworkUrl100 = artworkUrl100;
    }

    public Bitmap getArtwork100() {
        return mArtwork100;
    }

    public void setArtwork100(Bitmap artwork100) {
        mArtwork100 = artwork100;
    }

    public Double getCollectionPrice() {
        return mCollectionPrice;
    }

    public void setCollectionPrice(Double collectionPrice) {
        mCollectionPrice = collectionPrice;
    }

    public String getCollectionExplicitness() {
        return mCollectionExplicitness;
    }

    public void setCollectionExplicitness(String collectionExplicitness) {
        mCollectionExplicitness = collectionExplicitness;
    }

    public Integer getTrackCount() {
        return mTrackCount;
    }

    public void setTrackCount(Integer trackCount) {
        mTrackCount = trackCount;
    }

    public String getCopyright() {
        return mCopyright;
    }

    public void setCopyright(String copyright) {
        mCopyright = copyright;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
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

    public String getPrimaryGenreName() {
        return mPrimaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        mPrimaryGenreName = primaryGenreName;
    }

    public Integer getAmgArtistId() {
        return mAmgArtistId;
    }

    public void setAmgArtistId(Integer amgArtistId) {
        mAmgArtistId = amgArtistId;
    }

    public String getContentAdvisoryRating() {
        return mContentAdvisoryRating;
    }

    public void setContentAdvisoryRating(String contentAdvisoryRating) {
        mContentAdvisoryRating = contentAdvisoryRating;
    }

}
