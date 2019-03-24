package com.imran.sugartestapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("image")
    @Expose
    private ImageData image;

    @SerializedName("body_html")
    private String description;

    private int parentPosition;
    private int childPosition;

    public String getTitle() {
        return title;
    }

    public ImageData getImage() {
        return image;
    }

    public int getParentPosition() {
        return parentPosition;
    }

    public void setParentPosition(int parentPosition) {
        this.parentPosition = parentPosition;
    }

    public int getChildPosition() {
        return childPosition;
    }

    public void setChildPosition(int childPosition) {
        this.childPosition = childPosition;
    }

    public String getDescription() {
        return description;
    }
}
