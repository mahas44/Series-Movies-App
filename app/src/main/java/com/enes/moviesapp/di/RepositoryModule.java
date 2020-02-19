package com.enes.moviesapp.di;

import android.app.Application;

import com.enes.moviesapp.repository.MovieRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    MovieRepository providesMovieRepository(Application application){
        return new MovieRepository(application);
    }
}
