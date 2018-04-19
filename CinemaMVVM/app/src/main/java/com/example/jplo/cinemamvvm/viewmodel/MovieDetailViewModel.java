package com.example.jplo.cinemamvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.jplo.cinemamvvm.model.CinemaRepository;
import com.example.jplo.cinemamvvm.model.MovieDetail;

import javax.inject.Inject;

public class MovieDetailViewModel extends AndroidViewModel {

    private CinemaRepository cinemaRepository;
    private LiveData<MovieDetail> movieDetailObservable;

    @Inject
    public MovieDetailViewModel(@NonNull CinemaRepository cinemaRepository,
                                @NonNull Application application) {
        super(application);
        this.cinemaRepository = cinemaRepository;
    }

    public LiveData<MovieDetail> getMovieDetail(String id){
        movieDetailObservable = cinemaRepository.getMovieDetail(id);
        return movieDetailObservable;
    }
}
