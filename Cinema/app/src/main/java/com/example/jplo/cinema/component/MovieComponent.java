package com.example.jplo.cinema.component;

import com.example.jplo.cinema.module.MovieModule;
import com.example.jplo.cinema.service.MovieService;

import dagger.Component;

@Component(modules = MovieModule.class)
public interface MovieComponent {
    MovieService getMovieService();
}
