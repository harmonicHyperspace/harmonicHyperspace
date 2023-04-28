package com.example.harmonichyperspace.POJO;

import java.io.Serializable;
import java.util.List;

public class Artists implements Serializable {
    private List<Artist> items;

    public List<Artist> getItems() {
        return items;
    }

    public void setItems(List<Artist> items) {
        this.items = items;
    }
}
