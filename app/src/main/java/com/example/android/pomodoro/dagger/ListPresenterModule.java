package com.example.android.pomodoro.dagger;


import com.example.android.pomodoro.model.data.DataRepository;
import com.example.android.pomodoro.pomodorolist.PomodoroListContract;
import com.example.android.pomodoro.pomodorolist.PomodoroListPresenter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

@Module
public class ListPresenterModule {

    private PomodoroListContract.ListView view;

    @ListFragmentScope
    public ListPresenterModule(PomodoroListContract.ListView view){
        this.view = view;
    }

    @Provides
    @ListFragmentScope
    public PomodoroListContract.ListView providesListView(){
        return view;
    }

    @Provides
    @ListFragmentScope
    public Scheduler providesScheduler(){
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @ListFragmentScope
    public PomodoroListPresenter providesListPresenter(DataRepository repository){
        return new PomodoroListPresenter(view, repository, AndroidSchedulers.mainThread());
    }
}
