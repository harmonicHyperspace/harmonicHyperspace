package com.example.harmonichyperspace.DB;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.harmonichyperspace.harmonicHyperspace;
import com.example.harmonichyperspace.profile.User;

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

}
