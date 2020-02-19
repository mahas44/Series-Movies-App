package com.enes.moviesapp.model.series;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.enes.moviesapp.service.SeriesService;

public class SeriesDataSourceFactory extends DataSource.Factory {

    private SeriesService seriesService;
    private Application application;
    private SeriesDataSource seriesDataSource;
    private MutableLiveData<SeriesDataSource> mutableLiveData;


    public SeriesDataSourceFactory(SeriesService seriesService, Application application) {
        this.seriesService = seriesService;
        this.application = application;
        mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        seriesDataSource = new SeriesDataSource(seriesService,application);
        mutableLiveData.postValue(seriesDataSource);
        return seriesDataSource;
    }

    public MutableLiveData<SeriesDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
