package com.example.jplo.cinema.module;

import com.example.jplo.cinema.adapter.MoviesAdapter;
import com.example.jplo.cinema.scope.MoviesActivityScope;
import com.example.jplo.cinema.view.MoviesActivity;

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
