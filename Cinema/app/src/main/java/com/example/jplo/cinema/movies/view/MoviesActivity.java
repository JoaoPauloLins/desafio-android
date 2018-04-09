package com.example.jplo.cinema.movies.view;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jplo.cinema.R;
import com.example.jplo.cinema.movies.model.Movie;
import com.example.jplo.cinema.movies.presenter.MoviesPresenter;
import com.example.jplo.cinema.movies.scope.MoviesActivityScope;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

@MoviesActivityScope
public class MoviesActivity extends AppCompatActivity implements MoviesView {

    @BindView(R.id.movies_recycler_view)
    RecyclerView recyclerView;

    @Inject
    MoviesPresenter moviesPresenter;

    MoviesAdapter moviesAdapter;
    EndlessRecyclerViewScrollListener scrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        AndroidInjection.inject(this);
        ButterKnife.bind(this);

        moviesAdapter = new MoviesAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(moviesAdapter);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page) {
                moviesPresenter.loadMovies(page);
            }
        };
        recyclerView.addOnScrollListener(scrollListener);

        moviesPresenter.loadMovies(0);
    }

    @Override
    public void addMovies(List<Movie> movies) {
        moviesAdapter.addMovies(movies);
    }

    @Override
    public void addLoad(){
        moviesAdapter.addLoad();
    }

    @Override
    public void removeLoad(){
        moviesAdapter.removeLoad();
    }

    @Override
    public void finishLoad(){
        scrollListener.setLoaded();
    }

    @Override
    public void movieDetail(View view){
        moviesPresenter.showMovieDetail(this, view);
    }
}
