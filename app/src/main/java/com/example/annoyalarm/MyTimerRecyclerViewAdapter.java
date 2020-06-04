package com.example.annoyalarm;

import androidx.lifecycle.LiveData;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.annoyalarm.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyTimerRecyclerViewAdapter extends RecyclerView.Adapter<MyTimerRecyclerViewAdapter.ViewHolder> {

    private List<TimerModel> timers;

    public MyTimerRecyclerViewAdapter(List<TimerModel> timers) {
        this.timers = timers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_timer_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = timers.get(position);
        holder.mIdView.setText(String.valueOf(timers.get(position).id));
        holder.mContentView.setText("test");
        holder.mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putString("timerId", String.valueOf("42"));
                Navigation.findNavController(holder.mView)
                        .navigate(R.id.action_timerListFragment_to_timerFragment, b);
            }
        });
    }

    public void setTimers(List<TimerModel> timers) {
        this.timers = timers;
    }

    @Override
    public int getItemCount() {
        return timers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public TimerModel mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}