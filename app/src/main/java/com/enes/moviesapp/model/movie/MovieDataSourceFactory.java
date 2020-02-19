package com.enes.moviesapp.model.movie;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.enes.moviesapp.service.MovieService;

public class MovieDataSourceFactory extends DataSource.Factory {

    private MovieService movieService;
    private Application application;
    private MovieDataSource movieDataSource;
    private MutableLiveData<MovieDataSource> mutableLiveData;


    public MovieDataSourceFactory(MovieService movieService, Application application) {
        this.movieService = movieService;
        this.application = application;
        mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        movieDataSource = new MovieDataSource(movieService,application);
        mutableLiveData.postValue(movieDataSource);
        return movieDataSource;
    }

    public MutableLiveData<MovieDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
