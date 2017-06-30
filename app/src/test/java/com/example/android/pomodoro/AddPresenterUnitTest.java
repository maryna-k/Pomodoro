package com.example.android.pomodoro;


import com.example.android.pomodoro.addpomodoro.AddPomodoroContract;
import com.example.android.pomodoro.addpomodoro.AddPomodoroPresenter;
import com.example.android.pomodoro.model.Pomodoro;
import com.example.android.pomodoro.model.data.LocalDataRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Completable;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddPresenterUnitTest {

    @Mock
    AddPomodoroContract.View view;

    @Mock
    LocalDataRepository repository;

    AddPomodoroPresenter presenter;
    Pomodoro pomodoro = new Pomodoro("code");

    @Before
    public void setUp(){
        presenter = new AddPomodoroPresenter(view, repository);
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @After
    public void cleanUp(){
        RxJavaPlugins.reset();
    }

    @Test
    public void shouldShowPomodoroList(){
        when(repository.addPomodoro(pomodoro)).thenReturn(Completable.complete());
        presenter.savePomodoro(pomodoro);
        verify(view).returnToPomodoroList();
    }

    @Test
    public void shouldHandleSavingError(){
        when(repository.addPomodoro(pomodoro)).thenReturn(Completable.error(new Throwable()));
        presenter.savePomodoro(pomodoro);
        verify(view).showSavingErrorMessage();
    }
}
