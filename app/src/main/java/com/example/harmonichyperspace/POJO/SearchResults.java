package com.example.harmonichyperspace.POJO;

import java.io.Serializable;

public class SearchResults implements Serializable {
    private Tracks tracks;
    private Albums albums;
    private Artists artists;

    public Albums getAlbums() {
        return albums;
    }

    public void setAlbums(Albums albums) {
        this.albums = albums;
    }

    public Artists getArtists() {
        return artists;
    }

    public void setArtists(Artists artists) {
        this.artists = artists;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }
}
