package com.enes.moviesapp.di;

import com.enes.moviesapp.ui.view.SeriesFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, SeriesRepositoryModule.class})
public interface SeriesComponent {

    void inject(SeriesFragment seriesFragment);
}
