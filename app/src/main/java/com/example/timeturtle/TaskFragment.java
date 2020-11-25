package com.example.timeturtle;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timeturtle.database.DataBaseManager;
import com.example.timeturtle.helperclasses.EventAdapter;
import com.example.timeturtle.helperclasses.Task;

import java.util.ArrayList;
import java.util.Calendar;

public class TaskFragment extends Fragment {

    private static final String TAG = "task fragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView taskRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_task, container, false);

        DataBaseManager db = new DataBaseManager();
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());
        db.insertTask(c.getTime(),c.getTime(),"khara","eri fik");
        ArrayList<Task> tasks = db.getAllTasks();

        Log.d(TAG, "taskTester: " + tasks);

        String[] taskNames = new String[tasks.size()];

        for (int i = 0; i < tasks.size(); i++) {
            taskNames[i] = tasks.get(i).getName();
        }

        EventAdapter eventAdapter = new EventAdapter(tasks);
        taskRecycler.setAdapter(eventAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        taskRecycler.setLayoutManager(layoutManager);

        return taskRecycler;
    }
}
