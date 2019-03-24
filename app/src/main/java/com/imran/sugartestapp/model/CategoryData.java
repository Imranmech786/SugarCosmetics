package com.imran.sugartestapp.model;

public class CategoryData {

    private String url;
    private ImageData image;
    private String title;
    private String description;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setImage(ImageData image) {
        this.image = image;
    }

    public ImageData getImage() {
        return image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
