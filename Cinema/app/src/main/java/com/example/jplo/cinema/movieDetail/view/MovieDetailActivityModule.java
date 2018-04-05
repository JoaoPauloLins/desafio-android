package com.example.jplo.cinema.movieDetail.view;

import com.example.jplo.cinema.movieDetail.interactor.MovieDetailInteractor;
import com.example.jplo.cinema.movieDetail.interactor.MovieDetailInteractorImpl;
import com.example.jplo.cinema.movieDetail.presenter.MovieDetailPresenter;
import com.example.jplo.cinema.movieDetail.presenter.MovieDetailPresenterImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MovieDetailActivityModule {

    @Provides
    MovieDetailPresenter providerMovieDetailPresenter(MovieDetailView movieDetailView,
                                                      MovieDetailInteractor movieDetailInteractor) {
        return new MovieDetailPresenterImpl(movieDetailView, movieDetailInteractor);
    }

    @Provides
    MovieDetailInteractor provideMoviesInteractor(Retrofit retrofit){
        return new MovieDetailInteractorImpl(retrofit);
    }
}
