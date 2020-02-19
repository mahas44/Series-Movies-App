package com.enes.moviesapp.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.enes.moviesapp.repository.SeriesRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SeriesViewModelFactory implements ViewModelProvider.Factory {

    private final SeriesRepository seriesRepository;

    @Inject
    public SeriesViewModelFactory(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SeriesViewModel(seriesRepository);
    }
}
