package com.example.android.pomodoro.util;

import android.app.Application;

import com.example.android.pomodoro.dagger.AppComponent;
import com.example.android.pomodoro.dagger.AppModule;
import com.example.android.pomodoro.dagger.DaggerAppComponent;


public class PomodoroApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate(){
        super.onCreate();
        initDagger();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }

    public void initDagger(){
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        appComponent.inject(this);
    }
}
