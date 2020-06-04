package com.example.annoyalarm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class TimerViewModel extends ViewModel {
    private MutableLiveData<List<TimerModel>> timers = new MutableLiveData<>();

    public LiveData<List<TimerModel>> getTimers() {
        if(timers.getValue() == null) {
            timers.setValue(new LinkedList<TimerModel>());
        }
        return timers;
    }

    public void addTimer(TimerModel tm) {
        List<TimerModel> list = timers.getValue();
        list.add(tm);
        timers.postValue(list);
    }
}
