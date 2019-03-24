package com.imran.sugartestapp.model;

import java.util.List;

public class CategoryList {

    private String title;
    private List<CategoryData> categoryDataList;
    private boolean isAllProductVisibile;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CategoryData> getCategoryDataList() {
        return categoryDataList;
    }

    public void setCategoryDataList(List<CategoryData> categoryDataList) {
        this.categoryDataList = categoryDataList;
    }

    public boolean isAllProductVisibile() {
        return isAllProductVisibile;
    }

    public void setAllProductVisibile(boolean allProductVisibile) {
        isAllProductVisibile = allProductVisibile;
    }

}
