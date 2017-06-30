package com.example.android.pomodoro.pomodorolist;


import com.example.android.pomodoro.model.Pomodoro;
import com.example.android.pomodoro.model.data.DataRepository;
import com.example.android.pomodoro.pomodorolist.PomodoroListContract.ListView;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class PomodoroListPresenter implements PomodoroListContract.ListPresenter {

    private DataRepository repository;
    private ListView view;
    private Scheduler mainScheduler;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public PomodoroListPresenter(ListView view, DataRepository repository, Scheduler mainScheduler) {
        this.view = view;
        this.repository = repository;
        this.mainScheduler = mainScheduler;
    }

    @Override
    public void loadPomodoros() {
        compositeDisposable.add(repository.loadAllPomodoros()
                .subscribeOn(Schedulers.io())
                .observeOn(mainScheduler)
                .subscribeWith(new DisposableSingleObserver<List<Pomodoro>>() {
                    @Override
                    public void onSuccess(@NonNull List<Pomodoro> pomodoroList) {
                        if (!pomodoroList.isEmpty()) {
                            view.showPomodoroList(pomodoroList);
                        } else {
                            view.showNoPomodorosMessage();
                        }
                    }
                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showLoadingErrorMessage();
                    }
                }));
    }

    public void unsubscribe() {
        compositeDisposable.clear();
    }
}
