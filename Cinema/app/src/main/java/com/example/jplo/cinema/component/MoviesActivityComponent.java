package com.example.jplo.cinema.component;

import com.example.jplo.cinema.view.MoviesActivity;
import com.example.jplo.cinema.module.MoviesActivityModule;
import com.example.jplo.cinema.scope.MoviesActivityScope;

import dagger.Component;

@Component(modules = MoviesActivityModule.class, dependencies = MovieComponent.class)
@MoviesActivityScope
public interface MoviesActivityComponent {

    void injectMovieActivity(MoviesActivity moviesActivity);
}
