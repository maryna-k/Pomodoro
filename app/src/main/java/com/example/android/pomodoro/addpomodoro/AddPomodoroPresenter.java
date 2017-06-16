package com.example.android.pomodoro.addpomodoro;

import com.example.android.pomodoro.addpomodoro.AddPomodoroContract.View;
import com.example.android.pomodoro.model.Pomodoro;
import com.example.android.pomodoro.model.data.DataRepository;


public class AddPomodoroPresenter implements AddPomodoroContract.Presenter {

    private DataRepository repository;
    private View view;

    public AddPomodoroPresenter(View view, DataRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void savePomodoro(Pomodoro pomodoro) {
        repository.addPomodoro(pomodoro);
        view.showPomodoroList();
    }
}
