package com.enes.moviesapp;

import android.app.Application;

import com.enes.moviesapp.di.AppModule;
import com.enes.moviesapp.di.DaggerMovieComponent;
import com.enes.moviesapp.di.DaggerSeriesComponent;
import com.enes.moviesapp.di.MovieComponent;
import com.enes.moviesapp.di.SeriesComponent;

public class App extends Application {

    private static App app;
    private MovieComponent movieComponent;
    private SeriesComponent seriesComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        movieComponent = DaggerMovieComponent.builder().appModule(new AppModule(this)).build();

        seriesComponent = DaggerSeriesComponent.builder().appModule(new AppModule(this)).build();
    }

    public static App getApp() {
        return app;
    }

    public MovieComponent getMovieComponent() {
        return movieComponent;
    }

    public SeriesComponent getSeriesComponent() {
        return seriesComponent;
    }
}
