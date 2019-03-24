package com.imran.sugartestapp.dependencies.activityBuilder;

import com.imran.sugartestapp.activity.MainActivity;
import com.imran.sugartestapp.dependencies.modules.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivity();
}
