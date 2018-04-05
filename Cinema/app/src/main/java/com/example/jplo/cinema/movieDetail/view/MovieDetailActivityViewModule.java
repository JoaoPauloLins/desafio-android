package com.example.jplo.cinema.movieDetail.view;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MovieDetailActivityViewModule {
    @Binds
    abstract MovieDetailView bindMovieDetailView(MovieDetailActivity movieDetailActivity);
}
