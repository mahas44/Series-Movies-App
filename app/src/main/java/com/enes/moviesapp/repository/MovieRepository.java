package com.enes.moviesapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.enes.moviesapp.R;
import com.enes.moviesapp.model.movie.Movie;
import com.enes.moviesapp.model.movie.MovieDataSource;
import com.enes.moviesapp.model.movie.MovieDataSourceFactory;
import com.enes.moviesapp.model.movie.MovieResponse;
import com.enes.moviesapp.service.MovieService;
import com.enes.moviesapp.retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {

    private ArrayList<Movie> movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> mutableLiveData = new MutableLiveData<>();
    private Application application;
    private MovieService movieService;

    private LiveData<MovieDataSource> movieDataSourceLiveData;
    private Executor executor;
    private LiveData<PagedList<Movie>> moviesPagedList;

    public MovieRepository(Application application) {
        this.application = application;
        movieService = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
    }


    public LiveData<List<Movie>> getMovies() {

        Call<MovieResponse> call = movieService.getMovies(application.getApplicationContext().getString(R.string.TMDB_API_KEY));

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

    public LiveData<PagedList<Movie>> getMoviesPagedList(){

        MovieDataSourceFactory factory = new MovieDataSourceFactory(movieService,application);
        movieDataSourceLiveData = factory.getMutableLiveData();

        PagedList.Config config = (new PagedList.Config.Builder())
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(10)
                .setPageSize(20)
                .setPrefetchDistance(4)
                .build();

        executor = Executors.newFixedThreadPool(5);
        moviesPagedList = (new LivePagedListBuilder<Long, Movie>(factory,config))
                .setFetchExecutor(executor)
                .build();




        return moviesPagedList;
    }
}
