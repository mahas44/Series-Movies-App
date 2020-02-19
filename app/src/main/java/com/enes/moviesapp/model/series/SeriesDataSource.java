package com.enes.moviesapp.model.series;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.enes.moviesapp.R;
import com.enes.moviesapp.retrofit.RetrofitInstance;
import com.enes.moviesapp.service.SeriesService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesDataSource extends PageKeyedDataSource<Long, Series> {

    private SeriesService seriesService;
    private Application application;

    public SeriesDataSource(SeriesService seriesService, Application application) {
        this.seriesService = seriesService;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Series> callback) {
        seriesService = RetrofitInstance.getRetrofitInstance().create(SeriesService.class);
        Call<SeriesResponse> call = seriesService.getPopularMoviesWithPaging(application.getApplicationContext().getString(R.string.TMDB_API_KEY),1);

        call.enqueue(new Callback<SeriesResponse>() {
            @Override
            public void onResponse(Call<SeriesResponse> call, Response<SeriesResponse> response) {
                SeriesResponse seriesResponse = response.body();
                ArrayList<Series> series= new ArrayList<>();

                series = (ArrayList<Series>) seriesResponse.getResults();

                callback.onResult(series,null,(long)2);
            }

            @Override
            public void onFailure(Call<SeriesResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Series> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Series> callback) {
        seriesService = RetrofitInstance.getRetrofitInstance().create(SeriesService.class);
        Call<SeriesResponse> call = seriesService.getPopularMoviesWithPaging(application.getApplicationContext().getString(R.string.TMDB_API_KEY),params.key);

        call.enqueue(new Callback<SeriesResponse>() {
            @Override
            public void onResponse(Call<SeriesResponse> call, Response<SeriesResponse> response) {
                SeriesResponse seriesResponse = response.body();
                ArrayList<Series> series = new ArrayList<>();

                if (seriesResponse != null && seriesResponse.getResults() != null){
                    series = (ArrayList<Series>) seriesResponse.getResults();
                    callback.onResult(series,params.key+1);
                }

            }

            @Override
            public void onFailure(Call<SeriesResponse> call, Throwable t) {

            }
        });
    }
}
