package com.example.android.pomodoro.addpomodoro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.pomodoro.R;


public class AddPomodoroActivity extends AppCompatActivity {

    private final String LOG_TAG = this.getClass().getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pomodoro);
    }
}
