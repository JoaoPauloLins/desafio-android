package com.example.jplo.cinemamvvm.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jplo.cinemamvvm.R;
import com.example.jplo.cinemamvvm.model.MovieDetail;
import com.example.jplo.cinemamvvm.util.GlideApp;
import com.example.jplo.cinemamvvm.viewmodel.MovieDetailViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailView {

    @BindView(R.id.movie_detail_photo)
    ImageView movieImage;

    @BindView(R.id.movie_detail_title)
    TextView movieTitle;

    @BindView(R.id.movie_text_detail)
    TextView movieTextDetail;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    MovieDetailViewModel movieDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("key");

        movieDetailViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(MovieDetailViewModel.class);
        movieDetailViewModel.getMovieDetail(id)
                .observe(this, movieDetail -> showMovieDetail(movieDetail));
    }

    @Override
    public void showMovieDetail(MovieDetail movieDetail) {
        movieTitle.setText(movieDetail.getName());
        movieTextDetail.setText(movieDetail.getDescription());
        GlideApp.with(this)
                .load(movieDetail.getUrl())
                .override(1200, 400)
                .into(movieImage);
    }
}
