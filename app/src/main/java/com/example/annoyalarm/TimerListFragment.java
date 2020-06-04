package com.example.annoyalarm;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.annoyalarm.dummy.DummyContent;

import java.util.List;

/**
 * A fragment representing a list of Items.
 */
public class TimerListFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private TimerViewModel timers;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TimerListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TimerListFragment newInstance(int columnCount) {
        TimerListFragment fragment = new TimerListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timer_list_list, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        timers = new ViewModelProvider(requireActivity()).get(TimerViewModel.class);
        RecyclerView recyclerView = view.findViewById(R.id.rv_timers);

        if (mColumnCount <= 1) {
            LinearLayoutManager llm = new LinearLayoutManager(view.getContext());
            recyclerView.setLayoutManager(llm);
            DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                    llm.getOrientation());
            recyclerView.addItemDecoration(dividerItemDecoration);
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), mColumnCount));
        }
        final MyTimerRecyclerViewAdapter rcva = new MyTimerRecyclerViewAdapter(timers.getTimers().getValue());
        recyclerView.setAdapter(rcva);

        timers.getTimers().observe(getViewLifecycleOwner(), new Observer<List<TimerModel>>() {
            @Override
            public void onChanged(List<TimerModel> timerModels) {
                Log.e("LEL","MODEL HAS CHANGED");
                rcva.setTimers(timerModels);
                rcva.notifyDataSetChanged();
            }
        });

        view.findViewById(R.id.btn_new_timer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(TimerListFragment.this)
                        .navigate(R.id.action_timerListFragment_to_timerFragment);
            }
        });
    }
}