package com.example.android.pomodoro.model.data;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.example.android.pomodoro.model.Pomodoro;
import com.example.android.pomodoro.model.database.AppDatabase;
import com.example.android.pomodoro.model.database.PomodoroDao;
import com.example.android.pomodoro.util.Config;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;


public class LocalDataRepository implements DataRepository {

    private PomodoroDao pomodoroDao;
    private static LocalDataRepository INSTANCE;
    private final String LOG_TAG = LocalDataRepository.class.getSimpleName();

    public static LocalDataRepository getInstance(Context context){
        if(INSTANCE == null) {
            INSTANCE = new LocalDataRepository(context);
        }
        return INSTANCE;
    }

    private LocalDataRepository(Context context){
        AppDatabase db = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, Config.DATABASE_NAME)
                .build();
        this.pomodoroDao = db.pomodoroDao();
    }

    @Override
    public Single<List<Pomodoro>> loadAllPomodoros() {
        return Single.fromCallable(() -> pomodoroDao.getAll());
    }

    @Override
    public Completable addPomodoro(Pomodoro pomodoro) {
        return Completable.fromAction(() -> pomodoroDao.insertPomodoro(pomodoro));
    }

    @Override
    public void removePomodoro(int id) {
        pomodoroDao.deletePomodoroById(id);
    }
}
