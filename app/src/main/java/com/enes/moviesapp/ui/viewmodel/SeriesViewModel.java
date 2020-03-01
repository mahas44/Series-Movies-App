package com.enes.moviesapp.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.enes.moviesapp.AppController;
import com.enes.moviesapp.model.series.Series;
import com.enes.moviesapp.model.series.SeriesDataSourceFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SeriesViewModel extends ViewModel {

    private LiveData<PagedList<Series>> seriesPagedList;


    private AppController appController;
    private Executor executor;

    public SeriesViewModel(AppController appController) {
        this.appController = appController;
        init();
    }

    public void init(){
        SeriesDataSourceFactory factory = new SeriesDataSourceFactory(appController);

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();

        executor = Executors.newFixedThreadPool(5);
        seriesPagedList = (new LivePagedListBuilder<Long, Series>(factory,config))
                .setFetchExecutor(executor)
                .build();
    }

    public LiveData<PagedList<Series>> getSeriesPagedList() {
        return seriesPagedList;
    }
}
