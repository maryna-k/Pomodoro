package com.example.android.pomodoro.dagger;


import android.content.Context;

import com.example.android.pomodoro.model.data.DataRepository;
import com.example.android.pomodoro.model.data.LocalDataRepository;
import com.example.android.pomodoro.util.PomodoroApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private PomodoroApplication application;

    public AppModule(PomodoroApplication application){
        this.application = application;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return application;
    }

    @Provides
    @Singleton
    public DataRepository provideDataRepository() {
        return LocalDataRepository.getInstance(application);
    }
}
