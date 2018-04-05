package com.example.jplo.cinema.movieDetail.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jplo.cinema.R;
import com.example.jplo.cinema.movieDetail.model.MovieDetail;
import com.example.jplo.cinema.movieDetail.presenter.MovieDetailPresenter;
import com.example.jplo.cinema.util.GlideApp;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public class MovieDetailActivity
        extends AppCompatActivity
        implements MovieDetailView {

    @BindView(R.id.movie_detail_photo)
    ImageView movieImage;

    @BindView(R.id.movie_detail_title)
    TextView movieTitle;

    @BindView(R.id.movie_text_detail)
    TextView movieTextDetail;

    @Inject
    MovieDetailPresenter movieDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        AndroidInjection.inject(this);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("key");

        movieDetailPresenter.loadMovie(id);
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
