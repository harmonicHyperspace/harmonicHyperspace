package com.example.harmonichyperspace.POJO;

import java.io.Serializable;
import java.util.List;

public class Albums implements Serializable {
    private List<Album> items;

    public List<Album> getItems() {
        return items;
    }

    public void setItems(List<Album> items) {
        this.items = items;
    }
}
