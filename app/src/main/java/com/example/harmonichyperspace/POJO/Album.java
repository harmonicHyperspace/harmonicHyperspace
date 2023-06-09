package com.example.harmonichyperspace.POJO;

import java.io.Serializable;
import java.util.List;

public class Album implements Serializable {
    private String id;
    private String name;
    private List<Artist> artists;
    private List<Thumbnail> images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Thumbnail> getImages() {
        return images;
    }

    public void setImages(List<Thumbnail> images) {
        this.images = images;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
    }

    public String getArtistName() {
        if (artists != null && !artists.isEmpty()) {
            return artists.get(0).getName();
        }
        return "";
    }

    // Add a new method to get the first image URL
    public String getImageUrl() {
        if (images != null && !images.isEmpty()) {
            return images.get(0).getUrl();
        }
        return "";
    }
}
