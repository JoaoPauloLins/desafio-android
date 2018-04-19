package com.example.jplo.cinemamvvm.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Singleton
public class CinemaRepositoryImpl implements CinemaRepository {

    private CinemaService cinemaService;

    @Inject
    public CinemaRepositoryImpl(CinemaService cinemaService){
        this.cinemaService = cinemaService;
    }

    @Override
    public LiveData<List<Movie>> getMovies(int page, int size) {

        final MutableLiveData<List<Movie>> movies = new MutableLiveData<>();

        Observable<List<Movie>> moviesObservable =
                cinemaService.getMovies(page, size);
        moviesObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mo -> movies.setValue(mo));

        return movies;
    }

    @Override
    public LiveData<MovieDetail> getMovieDetail(String id) {

        final MutableLiveData<MovieDetail> movieDetail = new MutableLiveData<>();

        Observable<MovieDetail> movieDetailObservable =
                cinemaService.getMovieDetail(id);
        movieDetailObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mdo -> movieDetail.setValue(mdo));

        return movieDetail;
    }
}
