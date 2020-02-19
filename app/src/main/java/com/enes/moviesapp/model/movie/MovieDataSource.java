package com.enes.moviesapp.model.movie;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.enes.moviesapp.R;
import com.enes.moviesapp.service.MovieService;
import com.enes.moviesapp.retrofit.RetrofitInstance;

import java.util.ArrayList;
import com.enes.moviesapp.repository.MovieRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDataSource extends PageKeyedDataSource<Long, Movie> {

    private MovieService movieService;
    private Application application;

    public MovieDataSource(MovieService movieService, Application application) {
        this.movieService = movieService;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull final LoadInitialCallback<Long, Movie> callback) {
        movieService = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
        Call<MovieResponse> call = movieService.getPopularMoviesWithPaging(application.getApplicationContext().getString(R.string.TMDB_API_KEY),1);

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
        movieService = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
        Call<MovieResponse> call = movieService.getPopularMoviesWithPaging(application.getApplicationContext().getString(R.string.TMDB_API_KEY),params.key);

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
