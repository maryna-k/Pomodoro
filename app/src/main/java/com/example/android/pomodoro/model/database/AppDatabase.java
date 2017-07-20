package com.example.android.pomodoro.model.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.android.pomodoro.model.Pomodoro;


@Database(entities = Pomodoro.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PomodoroDao pomodoroDao();

}
