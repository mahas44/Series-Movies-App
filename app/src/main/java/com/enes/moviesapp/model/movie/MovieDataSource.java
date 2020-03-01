package com.enes.moviesapp.model.movie;


import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.enes.moviesapp.AppController;
import com.enes.moviesapp.BaseConstant;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long, Movie> implements BaseConstant {


    private AppController appController;

    public MovieDataSource(AppController appController) {
        this.appController = appController;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Movie> callback) {
        Call<MovieResponse> call = appController.getMovieService().getPopularMoviesWithPaging(TMDB_API_KEY,1);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                ArrayList<Movie> movies = new ArrayList<>();

                movies = (ArrayList<Movie>) movieResponse.getResults();

                callback.onResult(movies,null,(long)2);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Movie> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Long> params, @NonNull final LoadCallback<Long, Movie> callback) {
        Call<MovieResponse> call = appController.getMovieService().getPopularMoviesWithPaging(TMDB_API_KEY,params.key);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                ArrayList<Movie> movies = new ArrayList<>();

                if (movieResponse != null && movieResponse.getResults() != null){
                    movies = (ArrayList<Movie>) movieResponse.getResults();
                    callback.onResult(movies,params.key+1);
                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }
}
