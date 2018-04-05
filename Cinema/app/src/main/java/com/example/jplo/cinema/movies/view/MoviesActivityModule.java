package com.example.jplo.cinema.movies.view;

import com.example.jplo.cinema.movies.interactor.MoviesInteractor;
import com.example.jplo.cinema.movies.interactor.MoviesInteractorImpl;
import com.example.jplo.cinema.movies.presenter.MoviesPresenter;
import com.example.jplo.cinema.movies.presenter.MoviesPresenterImpl;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MoviesActivityModule {

    @Provides
    MoviesPresenter provideMoviesPresenter(MoviesView moviesView,
                                           MoviesInteractor moviesInteractor){
        return new MoviesPresenterImpl(moviesView, moviesInteractor);
    }

    @Provides
    MoviesInteractor provideMoviesInteractor(Retrofit retrofit){
        return new MoviesInteractorImpl(retrofit);
    }
}
