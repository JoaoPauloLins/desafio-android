package com.example.jplo.cinema.service;

import com.example.jplo.cinema.model.MovieDetail;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieDetailService {
    @GET("movies/detail/{id}")
    Observable<MovieDetail> getMovieDetail(@Path("id") String id);
}
