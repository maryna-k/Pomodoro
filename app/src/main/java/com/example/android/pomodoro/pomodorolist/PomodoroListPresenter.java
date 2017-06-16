package com.example.android.pomodoro.pomodorolist;


import com.example.android.pomodoro.model.data.DataRepository;
import com.example.android.pomodoro.model.Pomodoro;
import com.example.android.pomodoro.pomodorolist.PomodoroListContract.View;

import java.util.List;

public class PomodoroListPresenter implements PomodoroListContract.Presenter{

    private DataRepository repository;
    private View view;

    public PomodoroListPresenter(View view, DataRepository repository){
        this.view = view;
        this.repository = repository;
    }
    @Override
    public void loadPomodoros() {
        List<Pomodoro> list = repository.loadAllPomodoros();
        if(!list.isEmpty()){
            view.showPomodoros(list);
        }
    }
}
