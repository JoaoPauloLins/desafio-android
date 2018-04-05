package com.example.jplo.cinema.movies.interactor;

import com.example.jplo.cinema.movies.model.Movie;
import com.example.jplo.cinema.movies.service.MoviesService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class MoviesInteractorImpl implements MoviesInteractor {

    private Retrofit retrofit;

    @Inject
    public MoviesInteractorImpl(Retrofit retrofit){
        this.retrofit = retrofit;
    }


    @Override
    public Observable<List<Movie>> getMovies(int page, int size) {
        return retrofit.create(MoviesService.class).getMovies(page, size);
    }
}
