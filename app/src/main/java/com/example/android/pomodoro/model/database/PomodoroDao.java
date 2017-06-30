package com.example.android.pomodoro.model.database;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.android.pomodoro.model.Pomodoro;
import com.example.android.pomodoro.util.Config;

import java.util.List;


@Dao
public interface PomodoroDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPomodoro(Pomodoro pomodoro);

    @Query("SELECT * FROM " + Config.POMODORO_TABLE_NAME)
    List<Pomodoro> getAll();

    @Query("SELECT * FROM " + Config.POMODORO_TABLE_NAME + " WHERE id == :id")
    Pomodoro getPomodoroById(int id);

    @Query("DELETE FROM " + Config.POMODORO_TABLE_NAME + " WHERE id == :id")
    void deletePomodoroById(int id);

}
