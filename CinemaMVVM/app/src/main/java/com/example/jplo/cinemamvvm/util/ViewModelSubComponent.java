package com.example.jplo.cinemamvvm.util;

import com.example.jplo.cinemamvvm.viewmodel.MovieDetailViewModel;
import com.example.jplo.cinemamvvm.viewmodel.MoviesViewModel;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {

    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    MoviesViewModel moviesViewModel();
    MovieDetailViewModel movieDetailViewModel();
}
