package com.imran.sugartestapp.retrofit;


import com.imran.sugartestapp.model.CategoryJsonResponse;
import com.imran.sugartestapp.model.ProductJsonResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface APIInterface {

    //Category list
    @GET("category.json")
    Call<CategoryJsonResponse> getCategoryRespone();

    @GET
    Call<ProductJsonResponse> getProductRespone(@Url String url);


}
