package com.example.android.pomodoro.pomodorolist;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.pomodoro.addpomodoro.AddPomodoroActivity;
import com.example.android.pomodoro.model.data.LocalDataRepository;
import com.example.android.pomodoro.model.Pomodoro;
import com.example.android.pomodoro.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class PomodoroListFragment extends Fragment implements PomodoroListContract.View{

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.add_fab_button)
    FloatingActionButton addButton;

    private PomodoroAdapter adapter;
    private PomodoroListPresenter presenter;


    public PomodoroListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new PomodoroAdapter(new ArrayList<Pomodoro>(0));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pomodoro_list, container, false);
        ButterKnife.bind(this, rootView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        presenter = new PomodoroListPresenter(this,
                LocalDataRepository.getInstance(getActivity().getApplicationContext()));
        presenter.loadPomodoros();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddPomodoro();
            }
        });
        return rootView;
    }

    @Override
    public void showPomodoros(List<Pomodoro> pomodoroList) {
        adapter.replaceData(pomodoroList);
    }

    public void showAddPomodoro(){
        Intent intent = new Intent(getActivity(), AddPomodoroActivity.class);
        startActivity(intent);
    }
}
