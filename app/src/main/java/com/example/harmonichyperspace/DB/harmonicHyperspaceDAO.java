package com.example.harmonichyperspace.DB;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface harmonicHyperspaceDAO {

//    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.USER_TABLE + " WHERE mUserId = :userId ORDER BY mUserId DESC")
//    List<harmonicHyperspace> getHyperspaceByUserId(int userId);

    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User... users);

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.USER_TABLE)
    List<User> getAllUsers();

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.USER_TABLE + " WHERE mUserId = :userId")
    User getUserByUserId(int userId);

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.USER_TABLE + " WHERE mUsername = :username")
    User getUserByUsername(String username);

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.USER_TABLE + " WHERE mPassword = :password")
    User getUserByPassword(String password);

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.USER_TABLE + " WHERE mEmail = :email")
    User getUserByEmail(String email);

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.USER_TABLE + " WHERE mBio = :bio")
    User getUserByBio(String bio);

    @Insert
    void insert(SongReview... reviews);

    @Update
    void update(SongReview... reviews);

    @Delete
    void delete(SongReview... reviews);

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.TRACK_REVIEW_TABLE)
    List<SongReview> getAllSongReviews();

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.TRACK_REVIEW_TABLE + " WHERE mReviewId = :reviewId")
    SongReview getSongReviewByReviewId(int reviewId);

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.TRACK_REVIEW_TABLE + " WHERE mUserId = :userId")
    List<SongReview> getSongReviewByUserId(int userId);

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.TRACK_REVIEW_TABLE + " WHERE mTitle = :title")
    SongReview getSongReviewByTitle(String title);

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.TRACK_REVIEW_TABLE + " WHERE mReview = :review")
    SongReview getSongReviewByReview(String review);

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.TRACK_REVIEW_TABLE + " WHERE mRating = :rating")
    SongReview getSongReviewByRating(int rating);

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.TRACK_REVIEW_TABLE + " WHERE mSong = :song")
    SongReview getSongReviewBySong(String song);

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.TRACK_REVIEW_TABLE + " WHERE mArtist = :artist")
    SongReview getSongReviewByArtist(String artist);

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.TRACK_REVIEW_TABLE + " WHERE mAlbum = :album")
    SongReview getSongReviewByAlbum(String album);

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.TRACK_REVIEW_TABLE + " WHERE mCategory = :category")
    SongReview getSongReviewByCategoryId(int category);

    @Insert
    void insert(AlbumReview... reviews);

    @Update
    void update(AlbumReview... reviews);

    @Delete
    void delete(AlbumReview... reviews);

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.ALBUM_REVIEW_TABLE)
    List<AlbumReview> getAllAlbumReviews();

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.ALBUM_REVIEW_TABLE + " WHERE mReviewId = :reviewId")
    AlbumReview getAlbumReviewByReviewId(int reviewId);

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.ALBUM_REVIEW_TABLE + " WHERE mUserId = :userId")
    List<AlbumReview> getAlbumReviewByUserId(int userId);

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.ALBUM_REVIEW_TABLE + " WHERE mTitle = :title")
    AlbumReview getAlbumReviewByTitle(String title);

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.ALBUM_REVIEW_TABLE + " WHERE mReview = :review")
    AlbumReview getAlbumReviewByReview(String review);

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.ALBUM_REVIEW_TABLE + " WHERE mRating = :rating")
    AlbumReview getAlbumReviewByRating(int rating);

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.ALBUM_REVIEW_TABLE + " WHERE mAlbum = :album")
    AlbumReview getAlbumReviewByAlbumId(int album);

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.ALBUM_REVIEW_TABLE + " WHERE mArtist = :artist")
    AlbumReview getAlbumReviewByArtistId(int artist);

    @Query("SELECT * FROM " + harmonicHyperspaceDatabase.ALBUM_REVIEW_TABLE + " WHERE mCategory = :category")
    AlbumReview getAlbumReviewByCategoryId(int category);

}
