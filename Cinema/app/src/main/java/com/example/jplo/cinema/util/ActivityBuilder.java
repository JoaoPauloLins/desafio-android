package com.example.jplo.cinema.util;

import com.example.jplo.cinema.movieDetail.view.MovieDetailActivity;
import com.example.jplo.cinema.movieDetail.view.MovieDetailActivityModule;
import com.example.jplo.cinema.movieDetail.view.MovieDetailActivityViewModule;
import com.example.jplo.cinema.movies.view.MoviesActivity;
import com.example.jplo.cinema.movies.view.MoviesActivityModule;
import com.example.jplo.cinema.movies.view.MoviesActivityViewModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {MoviesActivityModule.class, MoviesActivityViewModule.class})
    abstract MoviesActivity bindMoviesActivity();

    @ContributesAndroidInjector(modules = {MovieDetailActivityModule.class, MovieDetailActivityViewModule.class})
    abstract MovieDetailActivity bindMovieDetailActivity();
}
