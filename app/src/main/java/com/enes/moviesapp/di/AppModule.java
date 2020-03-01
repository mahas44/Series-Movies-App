package com.enes.moviesapp.di;


import com.enes.moviesapp.AppController;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private AppController appController;

    public AppModule(AppController appController) {
        this.appController = appController;
    }

    @Singleton
    @Provides
    AppController providesAppController(){
        return appController;
    }
}
