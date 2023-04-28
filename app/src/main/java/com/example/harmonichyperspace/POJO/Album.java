package com.example.harmonichyperspace.POJO;

import java.io.Serializable;
import java.util.List;

public class Album implements Serializable {
    private String id;
    private String name;
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
}
