package com.example.timeturtle;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timeturtle.database.DataBaseManager;
import com.example.timeturtle.helperclasses.TaskAdapter;
import com.example.timeturtle.helperclasses.Task;
import com.example.timeturtle.helperclasses.TasksDatesAdapter;

import java.util.ArrayList;

public class TaskFragment extends Fragment {

    private static final String TAG = "task fragment";
    ArrayList<Task> tasks = new ArrayList<Task>();
    TaskAdapter taskAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        RecyclerView taskRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_task, container, false);
        taskAdapter = new TaskAdapter(tasks);
        taskRecycler.setAdapter(taskAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        taskRecycler.setLayoutManager(layoutManager);
        return taskRecycler;
    }

    public void fetchTasksFromDB(){
        DataBaseManager db = new DataBaseManager();
        tasks = db.getAllTasks();
    }

    public void fetchTasksFromDBDependentOnDate(String date) {
        DataBaseManager db = new DataBaseManager();
        tasks = db.getTasksPerDay(date);
    }
//    public void add(Task task) {
//        tasks.add(0, task);
//        taskAdapter.notifyItemInserted(0);
//    }
}
