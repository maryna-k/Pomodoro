package com.example.android.pomodoro.dagger;

import com.example.android.pomodoro.model.data.DataRepository;
import com.example.android.pomodoro.util.PomodoroApplication;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(PomodoroApplication app);

    DataRepository provideDataRepository();
}
