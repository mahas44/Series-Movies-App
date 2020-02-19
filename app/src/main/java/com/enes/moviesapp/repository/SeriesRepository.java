package com.enes.moviesapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.enes.moviesapp.R;
import com.enes.moviesapp.model.movie.Movie;
import com.enes.moviesapp.model.movie.MovieDataSourceFactory;
import com.enes.moviesapp.model.series.Series;
import com.enes.moviesapp.model.series.SeriesDataSource;
import com.enes.moviesapp.model.series.SeriesDataSourceFactory;
import com.enes.moviesapp.model.series.SeriesResponse;
import com.enes.moviesapp.retrofit.RetrofitInstance;
import com.enes.moviesapp.service.SeriesService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesRepository {

    private MutableLiveData<List<Series>> mutableLiveData = new MutableLiveData<>();
    private ArrayList<Series> series = new ArrayList<>();
    private Application application;

    private SeriesService seriesService;

    private Executor executor;
    private LiveData<PagedList<Series>> seriesPagedList;
    private LiveData<SeriesDataSource> seriesDataSourceLiveData;

    public SeriesRepository(Application application){
        this.application = application;
        seriesService = RetrofitInstance.getRetrofitInstance().create(SeriesService.class);
    }

    public LiveData<List<Series>> getSeries(){
        Call<SeriesResponse> call = seriesService.getSeries(application.getApplicationContext().getString(R.string.TMDB_API_KEY));

        call.enqueue(new Callback<SeriesResponse>() {
            @Override
            public void onResponse(Call<SeriesResponse> call, Response<SeriesResponse> response) {
                SeriesResponse seriesResponse = response.body();

                if (seriesResponse != null && seriesResponse.getResults() != null){
                    series = (ArrayList<Series>) seriesResponse.getResults();
                    mutableLiveData.setValue(series);
                }
            }

            @Override
            public void onFailure(Call<SeriesResponse> call, Throwable t) {

            }
        });


        return mutableLiveData;
    }


    public LiveData<PagedList<Series>> getSeriesPagedList(){

        SeriesDataSourceFactory factory = new SeriesDataSourceFactory(seriesService,application);
        seriesDataSourceLiveData = factory.getMutableLiveData();

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




        return seriesPagedList;
    }

}
