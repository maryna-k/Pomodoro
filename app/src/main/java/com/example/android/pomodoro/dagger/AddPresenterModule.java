package com.example.android.pomodoro.dagger;


import com.example.android.pomodoro.addpomodoro.AddPomodoroContract;
import com.example.android.pomodoro.addpomodoro.AddPomodoroPresenter;
import com.example.android.pomodoro.model.data.DataRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class AddPresenterModule {

    private AddPomodoroContract.View view;

    public AddPresenterModule(AddPomodoroContract.View view){
        this.view = view;
    }

    @Provides
    @AddFragmentScope
    AddPomodoroPresenter providesAddPresenter(DataRepository repository){
        return new AddPomodoroPresenter(view, repository);
    }
}
