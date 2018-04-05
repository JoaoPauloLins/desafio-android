package com.example.jplo.cinema.movies.interactor;

import com.example.jplo.cinema.movies.model.Movie;

import java.util.List;

import io.reactivex.Observable;

public interface MoviesInteractor {
    Observable<List<Movie>> getMovies(int page, int size);
}
