
package com.android.podonin.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("wrapperType")
    @Expose
    private String  mWrapperType;
    @SerializedName("collectionType")
    @Expose
    private String mCollectionType;
    @SerializedName("artistId")
    @Expose
    private Integer mArtistId;
    @SerializedName("collectionId")
    @Expose
    private Integer mCollectionId;
    @SerializedName("artistName")
    @Expose
    private String mArtistName;
    @SerializedName("collectionName")
    @Expose
    private String mCollectionName;
    @SerializedName("collectionCensoredName")
    @Expose
    private String mCollectionCensoredName;
    @SerializedName("artistViewUrl")
    @Expose
    private String mArtistViewUrl;
    @SerializedName("collectionViewUrl")
    @Expose
    private String mCollectionViewUrl;
    @SerializedName("artworkUrl60")
    @Expose
    private String mArtworkUrl60;
    @SerializedName("artworkUrl100")
    @Expose
    private String mArtworkUrl100;
    @SerializedName("collectionPrice")
    @Expose
    private Double mCollectionPrice;
    @SerializedName("collectionExplicitness")
    @Expose
    private String mCollectionExplicitness;
    @SerializedName("trackCount")
    @Expose
    private Integer mTrackCount;
    @SerializedName("copyright")
    @Expose
    private String mCopyright;
    @SerializedName("country")
    @Expose
    private String mCountry;
    @SerializedName("currency")
    @Expose
    private String mCurrency;
    @SerializedName("releaseDate")
    @Expose
    private String mReleaseDate;
    @SerializedName("primaryGenreName")
    @Expose
    private String mPrimaryGenreName;
    @SerializedName("amgArtistId")
    @Expose
    private Integer mAmgArtistId;
    @SerializedName("contentAdvisoryRating")
    @Expose
    private String mContentAdvisoryRating;

    //Track spec

    @SerializedName("kind")
    @Expose
    private String mKind;
    @SerializedName("trackId")
    @Expose
    private Integer mTrackId;
    @SerializedName("trackName")
    @Expose
    private String mTrackName;
    @SerializedName("trackCensoredName")
    @Expose
    private String mTrackCensoredName;
    @SerializedName("trackViewUrl")
    @Expose
    private String mTrackViewUrl;
    @SerializedName("previewUrl")
    @Expose
    private String mPreviewUrl;
    @SerializedName("artworkUrl30")
    @Expose
    private String mArtworkUrl30;
    @SerializedName("trackPrice")
    @Expose
    private Double mTrackPrice;
    @SerializedName("trackExplicitness")
    @Expose
    private String mTrackExplicitness;
    @SerializedName("discCount")
    @Expose
    private Integer mDiscCount;
    @SerializedName("discNumber")
    @Expose
    private Integer mDiscNumber;
    @SerializedName("trackNumber")
    @Expose
    private Integer mTrackNumber;
    @SerializedName("trackTimeMillis")
    @Expose
    private Integer mTrackTimeMillis;
    @SerializedName("isStreamable")
    @Expose
    private Boolean mIsStreamable;

    public String getWrapperType() {
        return mWrapperType;
    }

    public void setWrapperType(String wrapperType) {
        this.mWrapperType = wrapperType;
    }

    public String getCollectionType() {
        return mCollectionType;
    }

    public void setCollectionType(String collectionType) {
        this.mCollectionType = collectionType;
    }

    public Integer getArtistId() {
        return mArtistId;
    }

    public void setArtistId(Integer artistId) {
        this.mArtistId = artistId;
    }

    public Integer getCollectionId() {
        return mCollectionId;
    }

    public void setCollectionId(Integer collectionId) {
        this.mCollectionId = collectionId;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public void setArtistName(String artistName) {
        this.mArtistName = artistName;
    }

    public String getCollectionName() {
        return mCollectionName;
    }

    public void setCollectionName(String collectionName) {
        this.mCollectionName = collectionName;
    }

    public String getCollectionCensoredName() {
        return mCollectionCensoredName;
    }

    public void setCollectionCensoredName(String collectionCensoredName) {
        this.mCollectionCensoredName = collectionCensoredName;
    }

    public String getArtistViewUrl() {
        return mArtistViewUrl;
    }

    public void setArtistViewUrl(String artistViewUrl) {
        this.mArtistViewUrl = artistViewUrl;
    }

    public String getCollectionViewUrl() {
        return mCollectionViewUrl;
    }

    public void setCollectionViewUrl(String collectionViewUrl) {
        this.mCollectionViewUrl = collectionViewUrl;
    }

    public String getArtworkUrl60() {
        return mArtworkUrl60;
    }

    public void setArtworkUrl60(String artworkUrl60) {
        this.mArtworkUrl60 = artworkUrl60;
    }

    public String getArtworkUrl100() {
        return mArtworkUrl100;
    }

    public void setArtworkUrl100(String artworkUrl100) {
        this.mArtworkUrl100 = artworkUrl100;
    }

    public Double getCollectionPrice() {
        return mCollectionPrice;
    }

    public void setCollectionPrice(Double collectionPrice) {
        this.mCollectionPrice = collectionPrice;
    }

    public String getCollectionExplicitness() {
        return mCollectionExplicitness;
    }

    public void setCollectionExplicitness(String collectionExplicitness) {
        this.mCollectionExplicitness = collectionExplicitness;
    }

    public Integer getTrackCount() {
        return mTrackCount;
    }

    public void setTrackCount(Integer trackCount) {
        this.mTrackCount = trackCount;
    }

    public String getCopyright() {
        return mCopyright;
    }

    public void setCopyright(String copyright) {
        this.mCopyright = copyright;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        this.mCountry = country;
    }

    public String getCurrency() {
        return mCurrency;
    }

    public void setCurrency(String currency) {
        this.mCurrency = currency;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.mReleaseDate = releaseDate;
    }

    public String getPrimaryGenreName() {
        return mPrimaryGenreName;
    }

    public void setPrimaryGenreName(String primaryGenreName) {
        this.mPrimaryGenreName = primaryGenreName;
    }

    public Integer getAmgArtistId() {
        return mAmgArtistId;
    }

    public void setAmgArtistId(Integer amgArtistId) {
        this.mAmgArtistId = amgArtistId;
    }

    public String getContentAdvisoryRating() {
        return mContentAdvisoryRating;
    }

    public void setContentAdvisoryRating(String contentAdvisoryRating) {
        this.mContentAdvisoryRating = contentAdvisoryRating;
    }

    //Track speciffic

    public String getKind() {
        return mKind;
    }

    public void setKind(String kind) {
        this.mKind = kind;
    }

    public Integer getTrackId() {
        return mTrackId;
    }

    public void setTrackId(Integer trackId) {
        this.mTrackId = trackId;
    }

    public String getTrackName() {
        return mTrackName;
    }

    public void setTrackName(String trackName) {
        this.mTrackName = trackName;
    }

    public String getTrackCensoredName() {
        return mTrackCensoredName;
    }

    public void setTrackCensoredName(String trackCensoredName) {
        this.mTrackCensoredName = trackCensoredName;
    }

    public String getTrackViewUrl() {
        return mTrackViewUrl;
    }

    public void setTrackViewUrl(String trackViewUrl) {
        this.mTrackViewUrl = trackViewUrl;
    }

    public String getPreviewUrl() {
        return mPreviewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.mPreviewUrl = previewUrl;
    }

    public String getArtworkUrl30() {
        return mArtworkUrl30;
    }

    public void setArtworkUrl30(String artworkUrl30) {
        this.mArtworkUrl30 = artworkUrl30;
    }

    public Double getTrackPrice() {
        return mTrackPrice;
    }

    public void setTrackPrice(Double trackPrice) {
        this.mTrackPrice = trackPrice;
    }

    public String getTrackExplicitness() {
        return mTrackExplicitness;
    }

    public void setTrackExplicitness(String trackExplicitness) {
        this.mTrackExplicitness = trackExplicitness;
    }

    public Integer getDiscCount() {
        return mDiscCount;
    }

    public void setDiscCount(Integer discCount) {
        this.mDiscCount = discCount;
    }

    public Integer getDiscNumber() {
        return mDiscNumber;
    }

    public void setDiscNumber(Integer discNumber) {
        this.mDiscNumber = discNumber;
    }

    public Integer getTrackNumber() {
        return mTrackNumber;
    }

    public void setTrackNumber(Integer trackNumber) {
        this.mTrackNumber = trackNumber;
    }

    public Integer getTrackTimeMillis() {
        return mTrackTimeMillis;
    }

    public void setTrackTimeMillis(Integer trackTimeMillis) {
        this.mTrackTimeMillis = trackTimeMillis;
    }

    public Boolean getIsStreamable() {
        return mIsStreamable;
    }

    public void setIsStreamable(Boolean isStreamable) {
        this.mIsStreamable = isStreamable;
    }

}
