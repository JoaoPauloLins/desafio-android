package com.example.jplo.cinema.movies.view;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MoviesActivityViewModule {
    @Binds
    abstract MoviesView bindMoviesView(MoviesActivity moviesActivity);
}
