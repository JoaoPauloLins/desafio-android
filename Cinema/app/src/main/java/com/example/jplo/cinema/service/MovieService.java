package com.example.jplo.cinema.service;

import com.example.jplo.cinema.model.Movie;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
    @GET("movies/list")
    Observable<List<Movie>> getMovies(@Query("page") int page, @Query("size") int size);
}
