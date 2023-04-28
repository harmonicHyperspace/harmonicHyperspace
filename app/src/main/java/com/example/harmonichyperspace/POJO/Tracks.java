package com.example.harmonichyperspace.POJO;

import java.io.Serializable;
import java.util.List;

public class Tracks implements Serializable {
    private List<Track> items;

    public List<Track> getItems() {
        return items;
    }

    public void setItems(List<Track> items) {
        this.items = items;
    }
}
