package com.enes.moviesapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.enes.moviesapp.BaseConstant;
import com.enes.moviesapp.model.series.Series;
import com.enes.moviesapp.model.series.SeriesResponse;
import com.enes.moviesapp.retrofit.RetrofitInstance;
import com.enes.moviesapp.service.SeriesService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesRepository implements BaseConstant {

    private MutableLiveData<List<Series>> mutableLiveData = new MutableLiveData<>();
    private ArrayList<Series> series = new ArrayList<>();

    private SeriesService seriesService;

    public SeriesRepository(){
        seriesService = RetrofitInstance.getRetrofitInstance().create(SeriesService.class);
    }

    public LiveData<List<Series>> getSeries(){
        Call<SeriesResponse> call = seriesService.getSeries(TMDB_API_KEY);

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

}
