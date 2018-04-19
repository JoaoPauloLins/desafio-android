package com.example.jplo.cinemamvvm.model;

import android.arch.lifecycle.LiveData;

import java.util.List;

public interface CinemaRepository {

    LiveData<List<Movie>> getMovies(int page, int size);

    LiveData<MovieDetail> getMovieDetail(String id);
}
