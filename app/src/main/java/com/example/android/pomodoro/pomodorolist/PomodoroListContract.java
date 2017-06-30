package com.example.android.pomodoro.pomodorolist;


import com.example.android.pomodoro.model.Pomodoro;

import java.util.List;

public interface PomodoroListContract {

    interface ListPresenter {
        void loadPomodoros();
    }

    interface ListView {
        void showPomodoroList(List<Pomodoro> pomodoroList);
        void showNoPomodorosMessage();
        void showLoadingErrorMessage();
        void showAddPomodoro();
    }
}
