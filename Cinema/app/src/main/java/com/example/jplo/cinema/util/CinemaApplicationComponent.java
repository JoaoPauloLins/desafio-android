package com.example.jplo.cinema.util;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        CinemaApplicationModule.class,
        ActivityBuilder.class})
public interface CinemaApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        CinemaApplicationComponent build();
    }

    void inject(CinemaApplication cinemaApplication);
}
