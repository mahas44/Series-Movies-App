package com.enes.moviesapp;

import android.app.Application;
import android.content.Context;

import com.enes.moviesapp.di.AppModule;
import com.enes.moviesapp.di.DaggerMovieComponent;
import com.enes.moviesapp.di.DaggerSeriesComponent;
import com.enes.moviesapp.di.MovieComponent;
import com.enes.moviesapp.di.SeriesComponent;
import com.enes.moviesapp.retrofit.RetrofitInstance;
import com.enes.moviesapp.service.MovieService;
import com.enes.moviesapp.service.SeriesService;

public class AppController extends Application {

    private MovieService movieService;
    private SeriesService seriesService;
    private MovieComponent movieComponent;
    private SeriesComponent seriesComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        movieComponent = DaggerMovieComponent.builder().appModule(new AppModule(this)).build();
        seriesComponent = DaggerSeriesComponent.builder().appModule(new AppModule(this)).build();
    }

    private static AppController get(Context context){
        return (AppController) context.getApplicationContext();
    }

    public static AppController create(Context context){
        return AppController.get(context);
    }


    public MovieService getMovieService() {
        if (movieService == null){
            movieService = RetrofitInstance.getRetrofitInstance().create(MovieService.class);
        }
        return movieService;
    }

    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }

    public SeriesService getSeriesService() {
        if (seriesService == null){
            seriesService = RetrofitInstance.getRetrofitInstance().create(SeriesService.class);
        }
        return seriesService;
    }

    public void setSeriesService(SeriesService seriesService) {
        this.seriesService = seriesService;
    }

    public MovieComponent getMovieComponent() {
        return movieComponent;
    }

    public SeriesComponent getSeriesComponent() {
        return seriesComponent;
    }
}
