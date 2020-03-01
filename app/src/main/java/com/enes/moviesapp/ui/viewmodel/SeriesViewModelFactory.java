package com.enes.moviesapp.ui.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.enes.moviesapp.AppController;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SeriesViewModelFactory implements ViewModelProvider.Factory {

    private final AppController appController;

    @Inject
    public SeriesViewModelFactory(AppController appController) {
        this.appController = appController;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SeriesViewModel(appController);
    }
}
