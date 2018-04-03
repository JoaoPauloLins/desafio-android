package com.example.jplo.cinema.component;

import com.example.jplo.cinema.module.MovieDetailModule;
import com.example.jplo.cinema.service.MovieDetailService;

import dagger.Component;

@Component(modules = MovieDetailModule.class)
public interface MovieDetailComponent {
    MovieDetailService getMovieDetailService();
}
