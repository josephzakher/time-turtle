package com.example.timeturtle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.timeturtle.helperclasses.Task;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TaskFragment taskFragment = new TaskFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout_items, taskFragment);

        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
    }
}