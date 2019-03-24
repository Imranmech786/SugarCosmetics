package com.imran.sugartestapp.dependencies.modules;

import com.imran.sugartestapp.retrofit.APIInterface;
import com.imran.sugartestapp.viewModel.MainActivityViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    MainActivityViewModel mainActivityViewModel(APIInterface apiInterface) {
        return new MainActivityViewModel(apiInterface);
    }
}
