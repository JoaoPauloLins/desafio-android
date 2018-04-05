package com.example.jplo.cinema.movies.presenter;

import android.content.Context;
import android.view.View;

public interface MoviesPresenter {

    void loadMovies(int page);

    void showMovieDetail(Context context, View view);
}
