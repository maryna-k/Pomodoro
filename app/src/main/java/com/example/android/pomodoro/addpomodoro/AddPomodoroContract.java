package com.example.android.pomodoro.addpomodoro;


import com.example.android.pomodoro.model.Pomodoro;

public interface AddPomodoroContract {

    interface Presenter {
        void savePomodoro(Pomodoro pomodoro);
    }

    interface View {
        void showPomodoroList();
    }
}
