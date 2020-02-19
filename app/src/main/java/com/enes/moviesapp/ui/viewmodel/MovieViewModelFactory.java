package com.enes.moviesapp.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.enes.moviesapp.repository.MovieRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MovieViewModelFactory implements ViewModelProvider.Factory {

    private final MovieRepository movieRepository;

    @Inject
    public MovieViewModelFactory(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new MovieViewModel(movieRepository);
    }
}
