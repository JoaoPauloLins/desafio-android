package com.example.jplo.cinema.view;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesActivityModule {

    private final MoviesActivity moviesActivity;

    public MoviesActivityModule(MoviesActivity moviesActivity){
        this.moviesActivity = moviesActivity;
    }

    @Provides
    @MoviesActivityScope
    public MoviesAdapter moviesAdapter(){
        return new MoviesAdapter();
    }
}
