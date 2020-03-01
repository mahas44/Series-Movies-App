package com.enes.moviesapp.di;


import com.enes.moviesapp.repository.SeriesRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SeriesRepositoryModule {

    @Singleton
    @Provides
    SeriesRepository providesSeriesRepository(){
        return new SeriesRepository();
    }
}
