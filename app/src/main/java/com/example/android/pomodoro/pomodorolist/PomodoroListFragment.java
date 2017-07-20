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
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.pomodoro.R;
import com.example.android.pomodoro.addpomodoro.AddPomodoroActivity;
import com.example.android.pomodoro.dagger.AppComponent;
import com.example.android.pomodoro.dagger.DaggerListPresenterComponent;
import com.example.android.pomodoro.dagger.ListPresenterModule;
import com.example.android.pomodoro.model.Pomodoro;
import com.example.android.pomodoro.util.PomodoroApplication;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;



public class PomodoroListFragment extends Fragment implements PomodoroListContract.ListView {

    private final String LOG_TAG = this.getClass().getSimpleName();

    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;

    @BindView(R.id.no_pomodoros_view)
    TextView noPomodorosView;

    @BindView(R.id.add_fab_button)
    FloatingActionButton addButton;

    @Inject
    PomodoroListPresenter presenter;

    private PomodoroAdapter adapter;


    public PomodoroListFragment() {}


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new PomodoroAdapter(new ArrayList<Pomodoro>(0));

        //inject presenter
        AppComponent appComponent = ((PomodoroApplication) getActivity().getApplication()).getAppComponent();
        DaggerListPresenterComponent.builder()
                .appComponent(appComponent)
                .listPresenterModule(new ListPresenterModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pomodoro_list, container, false);
        ButterKnife.bind(this, rootView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        addButton.setOnClickListener(v -> showAddPomodoro());

        return rootView;
    }

    @Override
    public void onResume(){
        super.onResume();
        presenter.loadPomodoros();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.unsubscribe();
    }

    @Override
    public void showPomodoroList(List<Pomodoro> pomodoroList) {
        adapter.replaceData(pomodoroList);
        noPomodorosView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNoPomodorosMessage(){
        noPomodorosView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showLoadingErrorMessage(){
        Toast.makeText(getContext(), "Pomodoros could not be loaded...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showAddPomodoro(){
        Intent intent = new Intent(getActivity(), AddPomodoroActivity.class);
        startActivity(intent);
    }
}
