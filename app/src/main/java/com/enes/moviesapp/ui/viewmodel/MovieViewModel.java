package com.enes.moviesapp.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.enes.moviesapp.AppController;
import com.enes.moviesapp.model.movie.Movie;
import com.enes.moviesapp.model.movie.MovieDataSourceFactory;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MovieViewModel extends ViewModel {

    private LiveData<PagedList<Movie>> moviesPagedList;


    private AppController appController;
    private Executor executor;


    public MovieViewModel(AppController appController) {
        this.appController = appController;
        init();
    }

    private void init() {
        MovieDataSourceFactory factory = new MovieDataSourceFactory(appController);

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

    }

    public LiveData<PagedList<Movie>> getMoviesPagedList() {
        return moviesPagedList;
    }
}
