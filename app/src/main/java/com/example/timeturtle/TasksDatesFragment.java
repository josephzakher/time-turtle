package com.example.timeturtle;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.timeturtle.database.DataBaseManager;
import com.example.timeturtle.helperclasses.TaskAdapter;
import com.example.timeturtle.helperclasses.Task;
import com.example.timeturtle.helperclasses.TasksDatesAdapter;

import java.util.ArrayList;

public class TasksDatesFragment extends Fragment {

    private static final String TAG = "task fragment";
    DataBaseManager db = new DataBaseManager();
    ArrayList<String> dates = new ArrayList<String>();
    TasksDatesAdapter tasksDatesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView taskRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_taskdates, container, false);
        dates = db.getAllTasksDates();
        tasksDatesAdapter = new TasksDatesAdapter(dates, getActivity());
        taskRecycler.setAdapter(tasksDatesAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        taskRecycler.setLayoutManager(layoutManager);
        return taskRecycler;
    }
}
