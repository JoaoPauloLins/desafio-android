package com.example.jplo.cinemamvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;

import com.example.jplo.cinemamvvm.model.CinemaRepository;
import com.example.jplo.cinemamvvm.model.Movie;

import java.util.List;

import javax.inject.Inject;


public class MoviesViewModel extends AndroidViewModel {

    private CinemaRepository cinemaRepository;
    private LiveData<List<Movie>> moviesObservable;
    private static int size = 4;

    @Inject
    public MoviesViewModel(@NonNull CinemaRepository cinemaRepository,
                           @NonNull Application application) {
        super(application);
        this.cinemaRepository = cinemaRepository;
    }

    public LiveData<List<Movie>> getMovies(int page) {
        moviesObservable = cinemaRepository.getMovies(page,size);
        return moviesObservable;
    }
}
