package com.imran.sugartestapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryItem {

    @SerializedName("1")
    @Expose
    private String first_item_url;
    @SerializedName("2")
    @Expose
    private String second_item_url;
    @SerializedName("3")
    @Expose
    private String third_item_url;
    @SerializedName("4")
    @Expose
    private String fourth_item_url;

    public String getFirst_item_url() {
        return first_item_url;
    }

    public String getSecond_item_url() {
        return second_item_url;
    }

    public String getThird_item_url() {
        return third_item_url;
    }

    public String getFourth_item_url() {
        return fourth_item_url;
    }
}
