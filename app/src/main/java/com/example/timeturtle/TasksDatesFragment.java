package com.example.timeturtle;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timeturtle.database.DataBaseManager;
import com.example.timeturtle.helperclasses.DateElement;
import com.example.timeturtle.helperclasses.TasksDatesAdapter;

import java.util.ArrayList;

public class TasksDatesFragment extends Fragment {

    private static final String TAG = "task fragment";
    DataBaseManager db = new DataBaseManager();
    ArrayList<String> dates;
    TasksDatesAdapter tasksDatesAdapter;
    ArrayList<DateElement> dateElements;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView taskRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_taskdates, container, false);
        dates = db.getAllTasksDates();
        dateElements = new ArrayList<>();
        dates.forEach((k)-> dateElements.add(new DateElement(k,false)));
        tasksDatesAdapter = new TasksDatesAdapter(dateElements, getActivity());
        taskRecycler.setAdapter(tasksDatesAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,false);
        taskRecycler.setLayoutManager(layoutManager);
        return taskRecycler;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateSelected(int position){
        dateElements.forEach((k)-> k.setSelected(false));
        dateElements.get(position).setSelected(true);
        tasksDatesAdapter.notifyDataSetChanged();
    }
}
