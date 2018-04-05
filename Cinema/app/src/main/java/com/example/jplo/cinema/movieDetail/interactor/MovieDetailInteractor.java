package com.example.jplo.cinema.movieDetail.interactor;

import com.example.jplo.cinema.movieDetail.model.MovieDetail;

import io.reactivex.Observable;

public interface MovieDetailInteractor {
    Observable<MovieDetail> getMovieDetail(String id);
}
