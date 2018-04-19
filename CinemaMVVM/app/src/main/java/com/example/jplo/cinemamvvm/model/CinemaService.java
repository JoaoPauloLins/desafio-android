package com.example.jplo.cinemamvvm.model;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CinemaService {

    @GET("movies/list")
    Observable<List<Movie>> getMovies(@Query("page") int page, @Query("size") int size);

    @GET("movies/detail/{id}")
    Observable<MovieDetail> getMovieDetail(@Path("id") String id);
}
