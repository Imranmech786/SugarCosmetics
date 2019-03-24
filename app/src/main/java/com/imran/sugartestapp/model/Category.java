package com.imran.sugartestapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {

    @SerializedName("Lips")
    @Expose
    private List<CategoryItem> lips;
    @SerializedName("Face")
    @Expose
    private List<CategoryItem> face;
    @SerializedName("Eyes")
    @Expose
    private List<CategoryItem> eyes;

    public List<CategoryItem> getLips() {
        return lips;
    }

    public void setLips(List<CategoryItem> lips) {
        this.lips = lips;
    }

    public List<CategoryItem> getFace() {
        return face;
    }

    public void setFace(List<CategoryItem> face) {
        this.face = face;
    }

    public List<CategoryItem> getEyes() {
        return eyes;
    }

    public void setEyes(List<CategoryItem> eyes) {
        this.eyes = eyes;
    }
}


