package com.imran.sugartestapp.viewModel;

import android.util.Log;

import com.imran.sugartestapp.model.Category;
import com.imran.sugartestapp.model.CategoryJsonResponse;
import com.imran.sugartestapp.model.Product;
import com.imran.sugartestapp.model.ProductJsonResponse;
import com.imran.sugartestapp.navigator.BaseNavigator;
import com.imran.sugartestapp.retrofit.APIInterface;
import com.imran.sugartestapp.utils.StateLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends BaseViewModel<BaseNavigator> {

    public MainActivityViewModel(APIInterface apiInterface) {
        super(apiInterface);
    }

    private final StateLiveData<Category> listMutableLiveData = new StateLiveData<>();

    private final StateLiveData<Product> productStateLiveData = new StateLiveData<>();

    public StateLiveData<Category> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public StateLiveData<Product> getProductStateLiveData() {
        return productStateLiveData;
    }

    public void getCategoryList() {
        listMutableLiveData.postLoading();
        getApiInterface().getCategoryRespone()
                .enqueue(new Callback<CategoryJsonResponse>() {
                    @Override
                    public void onResponse(Call<CategoryJsonResponse> call, Response<CategoryJsonResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null && response.body().getCategory() != null && !response.body().getCategory().isEmpty()) {
                                CategoryJsonResponse categoryJsonResponse = response.body();
                                listMutableLiveData.postSuccess(categoryJsonResponse.getCategory().get(0));
                            } else {
                                listMutableLiveData.postComplete();
                            }
                        } else {
                            listMutableLiveData.postComplete();
                        }
                    }

                    @Override
                    public void onFailure(Call<CategoryJsonResponse> call, Throwable t) {
                        listMutableLiveData.postError(t);
                    }
                });
    }

    public void getProduct(final String url, final int parentPosition, final int childPosiiotn) {
        getApiInterface().getProductRespone(getUrl(url))
                .enqueue(new Callback<ProductJsonResponse>() {
                    @Override
                    public void onResponse(Call<ProductJsonResponse> call, Response<ProductJsonResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body() != null && response.body().getProducts() != null && !response.body().getProducts().isEmpty()) {
                                ProductJsonResponse productJsonResponse = response.body();
                                Product product = productJsonResponse.getProducts().get(0);
                                product.setParentPosition(parentPosition);
                                product.setChildPosition(childPosiiotn);
                                Log.i("ProductStateLiveData", "async_url" + url);
                                Log.i("ProductStateLiveData", "parentPosition " + parentPosition + " childPosiiotn  " + childPosiiotn);
                                Log.i("ProductStateLiveData", "async_image_path" + product.getImage().getImage());
                                Log.i("ProductStateLiveData", "async_title " + product.getTitle());
                                productStateLiveData.postSuccess(product);
                            } else {
                                productStateLiveData.postComplete();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ProductJsonResponse> call, Throwable t) {
                        productStateLiveData.postError(t);
                    }
                });
    }

    private String getUrl(String url) {
        String mainUrl = url;
        if (url.contains("jsonn")) {
            mainUrl = url.replace("jsonn", "json");
        }
        return mainUrl;
    }

}
