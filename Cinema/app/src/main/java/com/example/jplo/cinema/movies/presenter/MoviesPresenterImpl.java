package com.example.jplo.cinema.movies.presenter;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.jplo.cinema.movieDetail.view.MovieDetailActivity;
import com.example.jplo.cinema.movies.interactor.MoviesInteractor;
import com.example.jplo.cinema.movies.model.Movie;
import com.example.jplo.cinema.movies.view.MoviesView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MoviesPresenterImpl implements MoviesPresenter {

    private MoviesView moviesView;
    private MoviesInteractor moviesInteractor;

    @Inject
    public MoviesPresenterImpl(MoviesView moviesView, MoviesInteractor moviesInteractor) {
        this.moviesView = moviesView;
        this.moviesInteractor = moviesInteractor;
    }

    @Override
    public void loadMovies(int page) {
        int size = 3;
        Observable<List<Movie>> moviesObservable = moviesInteractor.getMovies(page, size);
        moviesObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movies -> moviesView.addMovies(movies));
    }

    @Override
    public void showMovieDetail(Context context, View view) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        String id = view.getTag().toString();
        intent.putExtra("key", id);
        context.startActivity(intent);
    }
}
