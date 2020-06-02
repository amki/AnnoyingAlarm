package com.example.annoyalarm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;

public class TimerFragment extends Fragment {

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
                NavHostFragment.findNavController(TimerFragment.this)
                        .navigate(R.id.action_timerFragment_to_timerListFragment);
            }
        });
    }
}