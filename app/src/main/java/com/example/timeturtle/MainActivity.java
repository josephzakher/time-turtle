package com.example.timeturtle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    TasksDatesFragment tasksDatesFragment = new TasksDatesFragment();
    AddTaskFragment addTaskFragment = new AddTaskFragment();
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    TaskFragment taskFragment = new TaskFragment();

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskFragment.fetchTasksFromDB();
        ft.add(R.id.content_frame, tasksDatesFragment);
        ft.add(R.id.content_frame, taskFragment);
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
                        taskFragment.fetchTasksFromDB();
                        if (ft != null && tasksDatesFragment.isVisible() && taskFragment.isVisible()) {
                            ft.remove(tasksDatesFragment);
                            ft.remove(taskFragment);
                            ft.add(R.id.content_frame, tasksDatesFragment);
                            ft.add(R.id.content_frame, taskFragment);
                        } else {
                            ft.remove(addTaskFragment);
                            ft.add(R.id.content_frame, tasksDatesFragment);
                            ft.add(R.id.content_frame, taskFragment);
                        }
                        ft.commit();
                        return true;
                    case nav_add:
                        navigationView.getMenu().getItem(0).setChecked(false);
                        navigationView.getMenu().getItem(1).setChecked(true);
                        ft = getSupportFragmentManager().beginTransaction();

                        if (ft != null && addTaskFragment.isVisible()) {
                        } else {
                            ft.remove(tasksDatesFragment);
                            ft.remove(taskFragment);
                            ft.add(R.id.content_frame, addTaskFragment);
                        }
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
            taskFragment.fetchTasksFromDB();
            ft.remove(addTaskFragment);
            ft.add(R.id.content_frame, tasksDatesFragment);
            ft.add(R.id.content_frame, taskFragment);
        } else {
            ft.remove(tasksDatesFragment);
            ft.remove(taskFragment);
            ft.add(R.id.content_frame, addTaskFragment);
        }
        ft.commit();
    }
    public void testing(String dates){
        taskFragment.fetchTasksFromDBDependentOnDate(dates);
        ft = getSupportFragmentManager().beginTransaction();
        ft.remove(taskFragment);
        ft.add(R.id.content_frame, taskFragment);
        ft.commit();
    }

}

