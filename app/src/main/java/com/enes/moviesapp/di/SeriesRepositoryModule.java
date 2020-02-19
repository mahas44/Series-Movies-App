package com.enes.moviesapp.di;

import android.app.Application;

import com.enes.moviesapp.repository.SeriesRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SeriesRepositoryModule {

    @Singleton
    @Provides
    SeriesRepository providesSeriesRepository(Application application){
        return new SeriesRepository(application);
    }
}
