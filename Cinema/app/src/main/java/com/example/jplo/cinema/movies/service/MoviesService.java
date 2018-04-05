package com.example.jplo.cinema.movies.service;

import com.example.jplo.cinema.movies.model.Movie;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesService {
    @GET("movies/list")
    Observable<List<Movie>> getMovies(@Query("page") int page, @Query("size") int size);
}
