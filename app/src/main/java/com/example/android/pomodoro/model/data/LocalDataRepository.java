package com.example.android.pomodoro.model.data;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.util.Log;

import com.example.android.pomodoro.model.database.AppDatabase;
import com.example.android.pomodoro.model.database.PomodoroDao;
import com.example.android.pomodoro.model.Pomodoro;
import com.example.android.pomodoro.util.Config;

import java.util.List;


public class LocalDataRepository implements DataRepository {

    private PomodoroDao pomodoroDao;
    private static LocalDataRepository INSTANCE;
    private static final String LOG_TAG = LocalDataRepository.class.getSimpleName();

    public static LocalDataRepository getInstance(Context context){
        if(INSTANCE == null) {
            INSTANCE = new LocalDataRepository(context);
        }
        return INSTANCE;
    }

    private LocalDataRepository(Context context){
        AppDatabase db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, Config.DATABASE_NAME)
                .allowMainThreadQueries()
                .build();
        this.pomodoroDao = db.pomodoroDao();
    }

    @Override
    public List<Pomodoro> loadAllPomodoros() {
        List<Pomodoro> list = pomodoroDao.getAll();
        for (int i = 0; i < list.size(); i++) {
            Log.v(LOG_TAG, "Pomodoro name: " + list.get(i).getName());
        }
        return list;
    }

    @Override
    public void addPomodoro(Pomodoro pomodoro) {
        pomodoroDao.insertPomodoro(pomodoro);
    }

    @Override
    public void removePomodoro(int id) {
        pomodoroDao.deletePomodoroById(id);
    }
}
