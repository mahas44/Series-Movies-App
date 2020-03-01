package com.enes.moviesapp.model.series;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.enes.moviesapp.AppController;

public class SeriesDataSourceFactory extends DataSource.Factory {

    private SeriesDataSource seriesDataSource;
    private MutableLiveData<SeriesDataSource> mutableLiveData;
    AppController appController;



    public SeriesDataSourceFactory(AppController appController) {
        this.appController = appController;
        this.mutableLiveData = new MutableLiveData<>();
    }

    @NonNull
    @Override
    public DataSource create() {
        seriesDataSource = new SeriesDataSource(appController);
        mutableLiveData.postValue(seriesDataSource);
        return seriesDataSource;
    }

    public MutableLiveData<SeriesDataSource> getMutableLiveData() {
        return mutableLiveData;
    }
}
