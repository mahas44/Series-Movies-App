package com.enes.moviesapp.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.enes.moviesapp.model.movie.Movie;
import com.enes.moviesapp.repository.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private MovieRepository movieRepository;
    private LiveData<List<Movie>> movieLiveData;
    private LiveData<PagedList<Movie>> moviesPagedList;

    public MovieViewModel(MovieRepository movieRepository) {


        this.movieRepository = movieRepository;
        this.moviesPagedList = movieRepository.getMoviesPagedList();
        this.movieLiveData = movieRepository.getMovies();
    }

    public LiveData<List<Movie>> getMovieLiveData(){
        return movieLiveData;
    }

    public LiveData<PagedList<Movie>> getMoviesPagedList() {
        return moviesPagedList;
    }
}
