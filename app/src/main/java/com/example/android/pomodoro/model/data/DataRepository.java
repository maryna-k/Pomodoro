package com.example.android.pomodoro.model.data;


import com.example.android.pomodoro.model.Pomodoro;

import java.util.List;

public interface DataRepository {

    List<Pomodoro> loadAllPomodoros();
    void addPomodoro(Pomodoro pomodoro);
    void removePomodoro(int id);
}
