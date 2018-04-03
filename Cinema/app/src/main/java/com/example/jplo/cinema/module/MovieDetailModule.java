package com.example.jplo.cinema.module;

import com.example.jplo.cinema.service.MovieDetailService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MovieDetailModule {

    @Provides
    public MovieDetailService movieDetailService(Retrofit retrofit){
        return retrofit.create(MovieDetailService.class);
    }

    @Provides
    public Retrofit retrofit(RxJava2CallAdapterFactory rxJava2CallAdapterFactory,
                             GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .baseUrl("https://desafio-mobile-pitang.herokuapp.com/")
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    public RxJava2CallAdapterFactory rxJava2CallAdapterFactory(){
        return RxJava2CallAdapterFactory.create();
    }

    @Provides GsonConverterFactory gsonConverterFactory(){
        return GsonConverterFactory.create();
    }
}
