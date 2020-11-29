package com.example.timeturtle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.timeturtle.database.DataBaseManager;
import com.example.timeturtle.helperclasses.Task;
import com.example.timeturtle.helperclasses.TaskAdapter;
import com.google.android.material.navigation.NavigationView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TaskFragment taskFragment = new TaskFragment();
    AddTaskFragment addTaskFragment = new AddTaskFragment();
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ft.replace(R.id.content_frame, taskFragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                final int nav_home = R.id.nav_home;
                final int nav_add = R.id.nav_add;
                setTitle(menuItem.getTitle());
                drawerLayout.closeDrawers();
                FragmentTransaction ft;
                switch (menuItem.getItemId()) {
                    case nav_home:
                        navigationView.getMenu().getItem(0).setChecked(true);
                        navigationView.getMenu().getItem(1).setChecked(false);
                        ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, taskFragment);
                        ft.commit();
                        return true;
                    case nav_add:
                        navigationView.getMenu().getItem(0).setChecked(false);
                        navigationView.getMenu().getItem(1).setChecked(true);
                        ft = getSupportFragmentManager().beginTransaction();
                        ft.replace(R.id.content_frame, addTaskFragment);
                        ft.commit();
                    default:
                        return false;
                }
            }
        });
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Toast.makeText(this, "sharing...", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addTask(View view) {
        ft = getSupportFragmentManager().beginTransaction();
        if (ft != null && addTaskFragment.isVisible()) {
            addTaskFragment.addTaskInDB();
            ft.replace(R.id.content_frame, taskFragment);
        } else {
            ft.replace(R.id.content_frame, addTaskFragment);
        }
        ft.commit();
    }

}

