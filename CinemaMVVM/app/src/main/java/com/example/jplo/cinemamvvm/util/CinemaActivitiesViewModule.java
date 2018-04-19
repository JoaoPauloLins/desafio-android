package com.example.jplo.cinemamvvm.util;

import com.example.jplo.cinemamvvm.view.MovieDetailActivity;
import com.example.jplo.cinemamvvm.view.MovieDetailView;
import com.example.jplo.cinemamvvm.view.MoviesActivity;
import com.example.jplo.cinemamvvm.view.MoviesView;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class CinemaActivitiesViewModule {

    @Binds
    abstract MoviesView bindMoviesView(MoviesActivity moviesActivity);

    @Binds
    abstract MovieDetailView bindMovieDetailView(MovieDetailActivity movieDetailActivity);
}
