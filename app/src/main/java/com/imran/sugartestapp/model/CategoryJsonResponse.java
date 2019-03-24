package com.imran.sugartestapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryJsonResponse {

    @SerializedName("category")
    @Expose
    private List<Category> category;

    public List<Category> getCategory() {
        return category;
    }

}
