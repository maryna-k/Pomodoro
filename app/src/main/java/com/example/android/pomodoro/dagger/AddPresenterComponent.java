package com.example.android.pomodoro.dagger;

import com.example.android.pomodoro.addpomodoro.AddPomodoroFragment;

import dagger.Component;


@AddFragmentScope
@Component(modules = {AddPresenterModule.class}, dependencies = {AppComponent.class})
public interface AddPresenterComponent {

    void inject(AddPomodoroFragment fragment);

}
