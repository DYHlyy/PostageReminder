package com.example.lyy.postagereminder;

/**
 * Created by lyy on 2017/7/26.
 */

public class Postage {

    private String name;
    private int imageId;

    public Postage(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
