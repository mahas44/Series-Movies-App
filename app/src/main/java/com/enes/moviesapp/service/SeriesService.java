package com.enes.moviesapp.service;

import com.enes.moviesapp.model.series.SeriesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SeriesService {


    @GET("trending/tv/week")
    Call<SeriesResponse> getSeries(@Query("api_key") String apiKey);

    @GET("tv/popular")
    Call<SeriesResponse> getPopularMoviesWithPaging(@Query("api_key") String apiKey, @Query("page") long page);
}
