package com.example.jplo.cinema.view;

import com.example.jplo.cinema.component.MovieComponent;
import com.example.jplo.cinema.service.MovieService;

import dagger.Component;

@Component(modules = MoviesActivityModule.class, dependencies = MovieComponent.class)
@MoviesActivityScope
public interface MoviesActivityComponent {

    void injectMovieActivity(MoviesActivity moviesActivity);
}
