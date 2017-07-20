package com.example.android.pomodoro.model.data;


import com.example.android.pomodoro.model.Pomodoro;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface DataRepository {

    Single<List<Pomodoro>> loadAllPomodoros();
    Completable addPomodoro(Pomodoro pomodoro);
    void removePomodoro(int id);
}
