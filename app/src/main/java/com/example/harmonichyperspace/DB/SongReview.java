package com.example.harmonichyperspace.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = harmonicHyperspaceDatabase.TRACK_REVIEW_TABLE)
public class SongReview {
    @PrimaryKey(autoGenerate = true)
    private int mReviewId;

    private int mUserId;

    private String mTitle;
    private String mReview;
    private String mRating;

    private String mSong;
    private String mArtist;
    private String mAlbum;
    private String mCategory;
//    private Date mDate;

    public SongReview(String title, String review, String rating, String song, String artist, String album, String category) {
        mTitle = title;
        mReview = review;
        mRating = rating;
        mSong = song;
        mArtist = artist;
        mAlbum = album;
        mCategory = category;
    }

    public int getReviewId() {
        return mReviewId;
    }

    public void setReviewId(int reviewId) {
        mReviewId = reviewId;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getReview() {
        return mReview;
    }

    public void setReview(String review) {
        mReview = review;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String rating) {
        mRating = rating;
    }

    public String getSong() {
        return mSong;
    }

    public void setSong(String song) {
        mSong = song;
    }

    public String getArtist() {
        return mArtist;
    }

    public void setArtist(String artist) {
        mArtist = artist;
    }

    public String getAlbum() {
        return mAlbum;
    }

    public void setAlbum(String album) {
        mAlbum = album;
    }

    public String getCategory() {
        return mCategory;
    }

    public void setCategory(String category) {
        mCategory = category;
    }

    //    public Date getDate() {
//        return mDate;
//    }
//
//    public void setDate(Date date) {
//        mDate = date;
//    }
}
