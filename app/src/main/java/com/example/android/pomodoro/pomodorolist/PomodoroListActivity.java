package com.example.android.pomodoro.pomodorolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.pomodoro.R;

public class PomodoroListActivity extends AppCompatActivity {

    private final String LOG_TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pomodoro_list);
    }
}
