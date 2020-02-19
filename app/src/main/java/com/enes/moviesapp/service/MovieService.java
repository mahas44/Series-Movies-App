package com.enes.moviesapp.service;

import com.enes.moviesapp.model.movie.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {


    @GET("trending/movie/week")
    Call<MovieResponse> getMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<MovieResponse> getPopularMoviesWithPaging(@Query("api_key") String apiKey, @Query("page") long page);
}
