package com.enes.moviesapp.model.movie;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.enes.moviesapp.AppController;


public class MovieDataSourceFactory extends DataSource.Factory {

    private AppController appController;
    private MovieDataSource movieDataSource;
    private MutableLiveData<MovieDataSource> mutableLiveData;




    public MovieDataSourceFactory(AppController appController) {
        this.appController = appController;
        this.mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        movieDataSource = new MovieDataSource(appController);
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
