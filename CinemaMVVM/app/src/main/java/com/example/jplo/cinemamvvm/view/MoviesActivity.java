package com.example.jplo.cinemamvvm.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jplo.cinemamvvm.R;
import com.example.jplo.cinemamvvm.model.Movie;
import com.example.jplo.cinemamvvm.viewmodel.MoviesViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class MoviesActivity extends AppCompatActivity implements MoviesView {

    @BindView(R.id.movies_recycler_view)
    RecyclerView recyclerView;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private MoviesAdapter moviesAdapter;
    private EndlessRecyclerViewScrollListener scrollListener;

    MoviesViewModel moviesViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);

        moviesAdapter = new MoviesAdapter();

        moviesViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MoviesViewModel.class);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(moviesAdapter);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page) {
                addMovies(page);
            }
        };
        recyclerView.addOnScrollListener(scrollListener);

        addMovies(0);
    }

    @Override
    public void addMovies(int page) {
        moviesAdapter.addLoad();
        moviesViewModel.getMovies(page)
                .observe(this, movies -> {
                    moviesAdapter.removeLoad();
                    moviesAdapter.addMovies(movies);
                    scrollListener.setLoaded();
                });
    }

    @Override
    public void movieDetail(View view) {
        Intent intent = new Intent(this, MovieDetailActivity.class);
        String id = view.getTag().toString();
        intent.putExtra("key", id);
        startActivity(intent);
    }
}
