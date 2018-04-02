package com.example.jplo.cinema.application;

import android.app.Activity;
import android.app.Application;

import com.example.jplo.cinema.component.DaggerMovieComponent;
import com.example.jplo.cinema.component.MovieComponent;

public class MovieApplication extends Application{

    private MovieComponent movieComponent;

    public static MovieApplication get(Activity activity){
        return (MovieApplication) activity.getApplication();
    }

    @Override
    public void onCreate(){
        super.onCreate();

        movieComponent = DaggerMovieComponent.builder().build();
    }

    public MovieComponent getMovieComponent() {
        return movieComponent;
    }
}
