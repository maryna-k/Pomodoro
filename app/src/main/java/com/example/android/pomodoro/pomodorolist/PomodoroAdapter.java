package com.example.android.pomodoro.pomodorolist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.pomodoro.R;
import com.example.android.pomodoro.model.Pomodoro;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;



public class PomodoroAdapter extends RecyclerView.Adapter<PomodoroAdapter.Holder> {

    private final String LOG_TAG = PomodoroAdapter.class.getSimpleName();

    private List<Pomodoro> pomodoroList;

    public PomodoroAdapter(List<Pomodoro> pomodoroList) {
        this.pomodoroList = pomodoroList;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.pomodoro_item, parent, false);
        return new Holder(row);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Pomodoro currentPomodoro = pomodoroList.get(position);
        holder.name.setText(currentPomodoro.getName());
    }

    @Override
    public int getItemCount() {
        return pomodoroList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.pomodoro_name)
        TextView name;

        public Holder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void addPomodoro(Pomodoro pomodoro) {
        pomodoroList.add(pomodoro);
        notifyDataSetChanged();
    }

    public void replaceData(List<Pomodoro> pomodoroList) {
        this.pomodoroList = pomodoroList;
        notifyDataSetChanged();
    }

}
