package com.example.android.pomodoro.addpomodoro;

import com.example.android.pomodoro.addpomodoro.AddPomodoroContract.View;
import com.example.android.pomodoro.model.Pomodoro;
import com.example.android.pomodoro.model.data.DataRepository;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;


public class AddPomodoroPresenter implements AddPomodoroContract.Presenter {

    private DataRepository repository;
    private View view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public AddPomodoroPresenter(View view, DataRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void savePomodoro(Pomodoro pomodoro) {
        repository.addPomodoro(pomodoro)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.trampoline())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.returnToPomodoroList();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        view.showSavingErrorMessage();
                    }
                });
    }

    public void unsubscribe() {
        compositeDisposable.clear();
    }
}
