package com.enes.moviesapp.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.enes.moviesapp.model.series.Series;
import com.enes.moviesapp.repository.SeriesRepository;

import java.util.List;

public class SeriesViewModel extends ViewModel {

    private SeriesRepository seriesRepository;

    private LiveData<PagedList<Series>> seriesPagedList;

    private LiveData<List<Series>> seriesLiveData;

    public SeriesViewModel(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;

        this.seriesPagedList = seriesRepository.getSeriesPagedList();
        this.seriesLiveData = seriesRepository.getSeries();
    }

    public LiveData<List<Series>> getSeriesResponseLiveData(){
        return seriesLiveData;
    }

    public LiveData<PagedList<Series>> getSeriesPagedList() {
        return seriesPagedList;
    }
}
