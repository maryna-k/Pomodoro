package com.example.android.pomodoro.pomodorolist;


import com.example.android.pomodoro.model.Pomodoro;

import java.util.List;

public interface PomodoroListContract {

    interface Presenter {
        void loadPomodoros();
    }

    interface View {
        void showPomodoros(List<Pomodoro> pomodoroList);
    }
}
