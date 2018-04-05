package com.example.jplo.cinema.util;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class CinemaApplicationModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    public Retrofit provideRetrofit(RxJava2CallAdapterFactory rxJava2CallAdapterFactory,
                                    GsonConverterFactory gsonConverterFactory){
        return new Retrofit.Builder()
                .baseUrl("https://desafio-mobile-pitang.herokuapp.com/")
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    public RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory(){
        return RxJava2CallAdapterFactory.create();
    }

    @Provides GsonConverterFactory provideGsonConverterFactory(){
        return GsonConverterFactory.create();
    }
}
