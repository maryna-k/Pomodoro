package com.example.android.pomodoro.dagger;


import com.example.android.pomodoro.pomodorolist.PomodoroListFragment;

import dagger.Component;

@ListFragmentScope
@Component (dependencies = AppComponent.class, modules = ListPresenterModule.class)
public interface ListPresenterComponent {

    void inject(PomodoroListFragment listFragment);

}
