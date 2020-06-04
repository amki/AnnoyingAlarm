package com.example.annoyalarm;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;

public class TimerFragment extends Fragment {

    private TimerViewModel timers;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timer, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv_timerId = view.findViewById(R.id.tv_timerId);
        TimePicker tp = view.findViewById(R.id.time_picker);
        timers = new ViewModelProvider(requireActivity()).get(TimerViewModel.class);
        Bundle b = this.getArguments();

        // If b is null this is a new timer to be created
        if(b == null) {
            tv_timerId.setText("New Timer");
        } else {
            tv_timerId.setText(b.getString("timerId"));
        }

        view.findViewById(R.id.button_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(TimerFragment.this)
                        .navigate(R.id.action_timerFragment_to_timerListFragment);
            }
        });

        view.findViewById(R.id.button_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("LEL","SAVING TIMER");
                Toast toast = Toast.makeText(view.getContext(), "Textlol", Toast.LENGTH_SHORT);
                TimerModel tm = new TimerModel();
                tm.id = 42;
                Log.e("LEL","ADDING TIMER NOW");
                timers.addTimer(tm);
                NavHostFragment.findNavController(TimerFragment.this)
                        .navigate(R.id.action_timerFragment_to_timerListFragment);
            }
        });
    }
}