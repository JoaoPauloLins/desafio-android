package com.example.jplo.cinema.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jplo.cinema.R;
import com.example.jplo.cinema.application.MovieApplication;
import com.example.jplo.cinema.component.DaggerMovieComponent;
import com.example.jplo.cinema.component.MovieComponent;
import com.example.jplo.cinema.model.Movie;
import com.example.jplo.cinema.service.MovieService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MoviesActivity extends AppCompatActivity {

    @BindView(R.id.movies_recycler_view)
    RecyclerView recyclerView;

    @Inject
    MovieService movieService;

    @Inject
    MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        MoviesActivityComponent moviesActivityComponent = DaggerMoviesActivityComponent.builder()
                .moviesActivityModule(new MoviesActivityModule(this))
                .movieComponent(MovieApplication.get(this).getMovieComponent())
                .build();
        moviesActivityComponent.injectMovieActivity(this);

        Observable<List<Movie>> moviesObservable = movieService.getMovies(0, 20);
        moviesObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mo -> moviesAdapter.setMovies(mo));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(moviesAdapter);
    }
}
