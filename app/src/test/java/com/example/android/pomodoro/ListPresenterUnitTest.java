package com.example.android.pomodoro;

import com.example.android.pomodoro.model.Pomodoro;
import com.example.android.pomodoro.model.data.LocalDataRepository;
import com.example.android.pomodoro.pomodorolist.PomodoroListContract;
import com.example.android.pomodoro.pomodorolist.PomodoroListPresenter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static java.util.Collections.EMPTY_LIST;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ListPresenterUnitTest {

    @Mock
    PomodoroListContract.ListView view;

    @Mock
    LocalDataRepository repository;

    PomodoroListPresenter presenter;
    final List<Pomodoro> MANY_POMODOROS = Arrays.asList(new Pomodoro("code"), new Pomodoro("cook"), new Pomodoro("play piano"));

    @Before
    public void setUp(){
        presenter = new PomodoroListPresenter(view, repository, Schedulers.trampoline());
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @After
    public void cleanUp(){
        RxJavaPlugins.reset();
    }

    @Test
    public void shouldPassPomodorosToView(){
        when(repository.loadAllPomodoros()).thenReturn(Single.just(MANY_POMODOROS));
        presenter.loadPomodoros();
        verify(view).showPomodoroList(MANY_POMODOROS);
    }

    @Test
    public void shouldShowNoPomodorosMessage(){
        when(repository.loadAllPomodoros()).thenReturn(Single.just(EMPTY_LIST));
        presenter.loadPomodoros();
        verify(view).showNoPomodorosMessage();
    }

    @Test
    public void shouldHandleLoadingError(){
        when(repository.loadAllPomodoros()).thenReturn(Single.error(new Throwable("Error loading pomodoros")));
        presenter.loadPomodoros();
        verify(view).showLoadingErrorMessage();
    }
}
