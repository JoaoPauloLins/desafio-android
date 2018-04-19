package com.example.jplo.cinemamvvm.util;

import android.app.Application;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;

import com.example.jplo.cinemamvvm.model.CinemaRepository;
import com.example.jplo.cinemamvvm.model.CinemaRepositoryImpl;
import com.example.jplo.cinemamvvm.model.CinemaService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(subcomponents = ViewModelSubComponent.class)
public class CinemaApplicationModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    public CinemaService provideRetrofit(RxJava2CallAdapterFactory rxJava2CallAdapterFactory,
                                    GsonConverterFactory gsonConverterFactory){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl("https://desafio-mobile-pitang.herokuapp.com/")
                .addCallAdapterFactory(rxJava2CallAdapterFactory)
                .addConverterFactory(gsonConverterFactory)
                .client(client)
                .build();

        return retrofit.create(CinemaService.class);
    }

    @Provides
    public RxJava2CallAdapterFactory provideRxJava2CallAdapterFactory(){
        return RxJava2CallAdapterFactory.create();
    }

    @Provides GsonConverterFactory provideGsonConverterFactory(){
        return GsonConverterFactory.create();
    }

    @Provides
    public CinemaRepository provideCinemaRepository(CinemaService cinemaService){
        return new CinemaRepositoryImpl(cinemaService);
    }

    @Singleton
    @Provides
    ViewModelProvider.Factory provideViewModelFactory(
            ViewModelSubComponent.Builder viewModelSubComponent) {

        return new CinemaViewModelFactory(viewModelSubComponent.build());
    }
}
