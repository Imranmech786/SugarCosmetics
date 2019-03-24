package com.imran.sugartestapp.viewModel;

import android.arch.lifecycle.ViewModel;

import com.imran.sugartestapp.retrofit.APIInterface;

public abstract class BaseViewModel<N> extends ViewModel {

    private N mNavigator;
    private APIInterface apiInterface;


    protected BaseViewModel(APIInterface apiInterface) {
        this.apiInterface = apiInterface;
    }


    public APIInterface getApiInterface() {
        return apiInterface;
    }

    public void setNavigator(N navigator) {
        this.mNavigator = navigator;
    }

    public N getNavigator() {
        return mNavigator;
    }
}
