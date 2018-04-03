package com.example.jplo.cinema.application;

import android.app.Activity;
import android.app.Application;

import com.example.jplo.cinema.component.DaggerMovieComponent;
import com.example.jplo.cinema.component.DaggerMovieDetailComponent;
import com.example.jplo.cinema.component.MovieComponent;
import com.example.jplo.cinema.component.MovieDetailComponent;

public class MovieApplication extends Application{

    private MovieComponent movieComponent;
    private MovieDetailComponent movieDetailComponent;

    public static MovieApplication get(Activity activity){
        return (MovieApplication) activity.getApplication();
    }

    @Override
    public void onCreate(){
        super.onCreate();

        movieComponent = DaggerMovieComponent.builder().build();
        movieDetailComponent = DaggerMovieDetailComponent.builder().build();
    }

    public MovieComponent getMovieComponent() {
        return movieComponent;
    }

    public MovieDetailComponent getMovieDetailComponent() {
        return movieDetailComponent;
    }
}
