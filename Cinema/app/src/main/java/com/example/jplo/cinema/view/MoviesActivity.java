package com.example.jplo.cinema.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.jplo.cinema.R;
import com.example.jplo.cinema.adapter.MoviesAdapter;
import com.example.jplo.cinema.application.MovieApplication;
import com.example.jplo.cinema.component.DaggerMoviesActivityComponent;
import com.example.jplo.cinema.component.MoviesActivityComponent;
import com.example.jplo.cinema.model.Movie;
import com.example.jplo.cinema.module.MoviesActivityModule;
import com.example.jplo.cinema.service.MovieService;

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

    private EndlessRecyclerViewScrollListener scrollListener;
    private MoviesActivityComponent moviesActivityComponent;
    private Observable<List<Movie>> moviesObservable;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        moviesActivityComponent = DaggerMoviesActivityComponent.builder()
                .moviesActivityModule(new MoviesActivityModule(this))
                .movieComponent(MovieApplication.get(this).getMovieComponent())
                .build();
        moviesActivityComponent.injectMovieActivity(this);

        moviesObservable = movieService.getMovies(0, 3);
        moviesObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mo -> moviesAdapter.setMovies(mo));

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(moviesAdapter);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                moviesObservable = movieService.getMovies(page, 3);
                moviesObservable.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mo -> moviesAdapter.addMovies(mo));
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
    }
}
