package com.example.jplo.cinema.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jplo.cinema.R;
import com.example.jplo.cinema.component.DaggerMovieDetailComponent;
import com.example.jplo.cinema.component.MovieDetailComponent;
import com.example.jplo.cinema.model.Movie;
import com.example.jplo.cinema.model.MovieDetail;
import com.example.jplo.cinema.module.GlideApp;
import com.example.jplo.cinema.service.MovieDetailService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailActivity extends AppCompatActivity {

    @BindView(R.id.movie_detail_photo)
    ImageView movieImage;

    @BindView(R.id.movie_detail_title)
    TextView movieTitle;

    @BindView(R.id.movie_text_detail)
    TextView movieTextDetail;

    MovieDetailService movieDetailService;
    MovieDetailComponent movieDetailComponent;
    Observable<MovieDetail> movieDetailObservable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String id = intent.getStringExtra("key");

        movieDetailComponent = DaggerMovieDetailComponent.builder().build();
        movieDetailService = movieDetailComponent.getMovieDetailService();
        movieDetailObservable = movieDetailService.getMovieDetail(id);
        movieDetailObservable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mdo -> {
                    movieTitle.setText(mdo.getName());
                    movieTextDetail.setText(mdo.getDescription());
                    GlideApp.with(this)
                            .load(mdo.getUrl())
                            .override(1200, 400)
                            .into(movieImage);
                });
    }
}
