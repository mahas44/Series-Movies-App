package com.enes.moviesapp.di;

import com.enes.moviesapp.ui.view.MovieFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, RepositoryModule.class})
public interface MovieComponent {

    void inject(MovieFragment movieFragment);
}
