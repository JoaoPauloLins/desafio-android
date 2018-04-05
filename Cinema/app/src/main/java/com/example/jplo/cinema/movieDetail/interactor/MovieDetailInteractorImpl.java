package com.example.jplo.cinema.movieDetail.interactor;

import com.example.jplo.cinema.movieDetail.model.MovieDetail;
import com.example.jplo.cinema.movieDetail.service.MovieDetailService;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class MovieDetailInteractorImpl implements MovieDetailInteractor {

    private Retrofit retrofit;

    @Inject
    public MovieDetailInteractorImpl (Retrofit retrofit) {
        this.retrofit = retrofit;
    }


    @Override
    public Observable<MovieDetail> getMovieDetail(String id) {
        return retrofit.create(MovieDetailService.class).getMovieDetail(id);
    }
}
