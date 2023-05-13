package com.example.harmonichyperspace.DB;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.harmonichyperspace.DB.harmonicHyperspaceDatabase;

@Entity(tableName = harmonicHyperspaceDatabase.USER_TABLE)
public class User {

    @PrimaryKey(autoGenerate = true)
    private int mUserId;

    private String mUsername;
    private String mPassword;

    private String mName;
    private String mEmail;
    private String mBio;
    private String mProfilePic;

    @ColumnInfo(name = "is_admin")
    private boolean isAdmin;


    public User(String username, String password, String email,boolean isAdmin) {
        mUsername = username;
        mPassword = password;
        mEmail = email;
        this.isAdmin = isAdmin;

    }
    public User(String username, String password, String email, String name, String bio, String profilePic, boolean isAdmin) {
        mUsername = username;
        mPassword = password;
        mEmail = email;
        mName = name;
        mBio = bio;
        mProfilePic = profilePic;
        this.isAdmin = isAdmin;
    }

//    public User(String username, String password, String email){
//        mUsername = username;
//        mPassword = password;
//        mEmail = email;
//    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getBio() {
        return mBio;
    }

    public void setBio(String bio) {
        mBio = bio;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getProfilePic() {
        return mProfilePic;
    }

    public void setProfilePic(String profilePic) {
        mProfilePic = profilePic;
    }
}
