package com.example.jplo.cinemamvvm.util;

import com.example.jplo.cinemamvvm.model.CinemaRepository;
import com.example.jplo.cinemamvvm.model.CinemaRepositoryImpl;
import com.example.jplo.cinemamvvm.model.CinemaService;

import dagger.Module;
import dagger.Provides;

@Module
public class CinemaActivitiesModule {

    @Provides
    CinemaRepository cinemaRepository(CinemaService cinemaService){
        return new CinemaRepositoryImpl(cinemaService);
    }
}
