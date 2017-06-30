package com.example.android.pomodoro.addpomodoro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.pomodoro.R;
import com.example.android.pomodoro.model.Pomodoro;
import com.example.android.pomodoro.model.data.LocalDataRepository;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AddPomodoroFragment extends Fragment implements AddPomodoroContract.View {

    private final String LOG_TAG = this.getClass().getSimpleName();

    @BindView(R.id.confirm_add_pomodoro_button)
    Button confirmNewPomodoroButton;

    @BindView(R.id.add_pomodoro_text_field)
    TextView addPomodoroName;

    private AddPomodoroPresenter presenter;

    public AddPomodoroFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_pomodoro, container, false);
        ButterKnife.bind(this, rootView);

        presenter = new AddPomodoroPresenter(this,
                LocalDataRepository.getInstance(getActivity().getApplicationContext()));

        confirmNewPomodoroButton.setOnClickListener(v -> {
            String name = addPomodoroName.getText().toString();
            Pomodoro pomodoro = new Pomodoro(name);
            presenter.savePomodoro(pomodoro);
        });
        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.unsubscribe();
    }

    @Override
    public void returnToPomodoroList(){
        getActivity().finish();
    }

    @Override
    public void showSavingErrorMessage(){
        Toast.makeText(getContext(), "Pomodoro was not saved, try again...", Toast.LENGTH_SHORT);
    }
}
