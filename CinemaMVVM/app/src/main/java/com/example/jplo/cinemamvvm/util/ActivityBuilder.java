package com.example.jplo.cinemamvvm.util;

import com.example.jplo.cinemamvvm.view.MovieDetailActivity;
import com.example.jplo.cinemamvvm.view.MoviesActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {CinemaActivitiesModule.class, CinemaActivitiesViewModule.class})
    abstract MoviesActivity bindMoviesActivity();

    @ContributesAndroidInjector(modules = {CinemaActivitiesModule.class, CinemaActivitiesViewModule.class})
    abstract MovieDetailActivity bindMovieDetailActivity();
}
