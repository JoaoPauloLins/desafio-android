package com.example.jplo.cinema.movieDetail.presenter;

import com.example.jplo.cinema.movieDetail.interactor.MovieDetailInteractor;
import com.example.jplo.cinema.movieDetail.model.MovieDetail;
import com.example.jplo.cinema.movieDetail.view.MovieDetailView;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailPresenterImpl implements MovieDetailPresenter {

    MovieDetailView movieDetailView;
    MovieDetailInteractor movieDetailInteractor;

    @Inject
    public MovieDetailPresenterImpl(MovieDetailView movieDetailView,
                                    MovieDetailInteractor movieDetailInteractor){
        this.movieDetailView = movieDetailView;
        this.movieDetailInteractor = movieDetailInteractor;
    }

    @Override
    public void loadMovie(String id) {

        Observable<MovieDetail> movieDetailObservable = movieDetailInteractor.getMovieDetail(id);
        movieDetailObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieDetail -> movieDetailView.showMovieDetail(movieDetail));
    }
}
