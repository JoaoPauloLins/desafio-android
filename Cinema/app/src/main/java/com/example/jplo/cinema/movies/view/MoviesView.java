package com.example.jplo.cinema.movies.view;

import android.view.View;

import com.example.jplo.cinema.movies.model.Movie;

import java.util.List;

public interface MoviesView {

    void addMovies(List<Movie> movies);

    void addLoad();

    void removeLoad();

    void finishLoad();

    void movieDetail(View view);
}
