package com.enes.moviesapp.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.enes.moviesapp.BaseConstant;
import com.enes.moviesapp.model.movie.Movie;
import com.enes.moviesapp.model.movie.MovieResponse;
import com.enes.moviesapp.retrofit.RetrofitInstance;
import com.enes.moviesapp.service.MovieService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository implements BaseConstant {

    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    private MovieService movieService;

    public MovieRepository() {
        movieService = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
    }


    public LiveData<List<Movie>> getMovies() {

        Call<MovieResponse> call = movieService.getMovies(TMDB_API_KEY);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();

                if (movieResponse != null && movieResponse.getResults() != null){
                    movies = (ArrayList<Movie>) movieResponse.getResults();
                    mutableLiveData.setValue(movies);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });


        return mutableLiveData;
    }
}
