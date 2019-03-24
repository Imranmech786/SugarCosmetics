package com.imran.sugartestapp.activity;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.imran.sugartestapp.R;
import com.imran.sugartestapp.adapter.CategoryAdapter;
import com.imran.sugartestapp.model.Category;
import com.imran.sugartestapp.model.CategoryData;
import com.imran.sugartestapp.model.CategoryItem;
import com.imran.sugartestapp.model.CategoryList;
import com.imran.sugartestapp.model.Product;
import com.imran.sugartestapp.utils.Network;
import com.imran.sugartestapp.utils.StateData;
import com.imran.sugartestapp.viewModel.MainActivityViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements CategoryAdapter.CallAsyncApi {

    @Inject
    MainActivityViewModel mainActivityModule;
    private RecyclerView recyclerview;
    private ProgressBar progressBar;
    private CategoryAdapter categoryAdapter;
    private TextView retry;

    @Override
    protected int getResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpActionBar("Sugar Cosmetics");
        progressBar = findViewById(R.id.progress_bar);
        retry = findViewById(R.id.retry);
        recyclerview = findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setItemAnimator(null);

        requestForCategory();

        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestForCategory();
            }
        });


    }

    private void requestForCategory() {
        if (Network.isConnected(getApplicationContext())) {
            retry.setVisibility(View.GONE);
            getCategory();
        } else {
            retry.setVisibility(View.VISIBLE);
            retry.setText(getString(R.string.check_internet_connection));
        }
    }

    private void getCategory() {
        mainActivityModule.getCategoryList();
        mainActivityModule.getListMutableLiveData().observe(this, new Observer<StateData<Category>>() {
            @Override
            public void onChanged(@Nullable StateData<Category> listStateData) {
                assert listStateData != null;
                switch (listStateData.getStatus()) {
                    case LOADING:
                        progressBar.setVisibility(View.VISIBLE);
                        break;
                    case SUCCESS:
                        progressBar.setVisibility(View.GONE);
                        Category category = listStateData.getData();
                        assert category != null;
                        List<CategoryList> categoryLists = getList(category);
                        categoryAdapter = new CategoryAdapter(MainActivity.this, categoryLists, MainActivity.this);
                        recyclerview.setAdapter(categoryAdapter);
                        break;
                    case ERROR:
                        progressBar.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }

    private List<CategoryList> getList(Category category) {
        List<CategoryList> arrayList = new ArrayList<>();
        arrayList.add(getCategoryList(category.getLips(), "Lips"));
        arrayList.add(getCategoryList(category.getFace(), "Face"));
        arrayList.add(getCategoryList(category.getEyes(), "Eyes"));
        return arrayList;
    }

    private CategoryList getCategoryList(List<CategoryItem> lipsCategory, String title) {
        CategoryList categoryList = new CategoryList();
        categoryList.setTitle(title);
        List<CategoryData> categoryDataList = new ArrayList<>();
        CategoryData categoryData;
        categoryData = new CategoryData();
        categoryData.setUrl(lipsCategory.get(0).getFirst_item_url());
        categoryDataList.add(categoryData);

        categoryData = new CategoryData();
        categoryData.setUrl(lipsCategory.get(0).getSecond_item_url());
        categoryDataList.add(categoryData);

        categoryData = new CategoryData();
        categoryData.setUrl(lipsCategory.get(0).getThird_item_url());
        categoryDataList.add(categoryData);

        categoryData = new CategoryData();
        categoryData.setUrl(lipsCategory.get(0).getFourth_item_url());
        categoryDataList.add(categoryData);

        categoryList.setCategoryDataList(categoryDataList);
        return categoryList;
    }


    public void requestForItemData(int parentPosition, int childPosiiotn, String url) {
        mainActivityModule.getProduct(url, parentPosition, childPosiiotn);
        mainActivityModule.getProductStateLiveData().observe(this, new Observer<StateData<Product>>() {
            @Override
            public void onChanged(@Nullable StateData<Product> productStateData) {
                assert productStateData != null;
                List<CategoryList> categoryLists = categoryAdapter.getmList();
                CategoryList list;
                final CategoryData categoryData;
                final Product product;
                switch (productStateData.getStatus()) {
                    case SUCCESS:
                        product = productStateData.getData();
                        assert product != null;
                        list = categoryLists.get(product.getParentPosition());
                        categoryData = list.getCategoryDataList().get(product.getChildPosition());
                        categoryData.setImage(product.getImage());
                        categoryData.setTitle(product.getTitle());
                        categoryData.setDescription(product.getDescription());
                        categoryData.setUrl(null);
                        if (recyclerview.isComputingLayout()) {
                            new Handler().post(new Runnable() {
                                @Override
                                public void run() {
                                    categoryAdapter.notifyItemChanged(product.getParentPosition(), CategoryAdapter.PAYLOAD_UPDATE_PRODUCT);
                                }
                            });
                        } else {
                            categoryAdapter.notifyItemChanged(product.getParentPosition(), CategoryAdapter.PAYLOAD_UPDATE_PRODUCT);
                        }
                        break;
                }
            }
        });
    }

    @Override
    public void callAsyncApi(int parentPosition, int childPosiiotn, String url) {
        requestForItemData(parentPosition, childPosiiotn, url);
    }
}
