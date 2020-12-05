package com.example.timeturtle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.widget.Toolbar;

import com.example.timeturtle.database.DataBaseManager;
import com.example.timeturtle.helperclasses.Task;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TasksDatesFragment tasksDatesFragment = new TasksDatesFragment();
    AddTaskFragment addTaskFragment = new AddTaskFragment();
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    TaskFragment taskFragment = new TaskFragment();
    DrawerLayout drawerLayout;
    private static final String TAG = "MainActivity";
    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";
    boolean useDarkTheme;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if (useDarkTheme) {

            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        super.onCreate(savedInstanceState);
        activity = this;
        setContentView(R.layout.activity_main);
        taskFragment.fetchTasksFromDB();

        if (!tasksDatesFragment.isVisible()) {
            ft.replace(R.id.content_frame, tasksDatesFragment);
        }
        if (!taskFragment.isVisible()) {
            ft.add(R.id.content_frame, taskFragment);
        }
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        myToolbar.setNavigationIcon(R.drawable.icon_menu);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle mDrawerToggle;
        mDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                myToolbar,
                R.string.drawer_opened,
                R.string.drawer_closed) {
        };

        drawerLayout.addDrawerListener(mDrawerToggle);
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
                        if (tasksDatesFragment.isVisible() && taskFragment.isVisible()) {
                            ft.remove(tasksDatesFragment);
                            ft.remove(taskFragment);
                        } else {
                            ft.remove(addTaskFragment);
                        }
                        ft.add(R.id.content_frame, tasksDatesFragment);
                        ft.add(R.id.content_frame, taskFragment);
                        ft.commit();
                        return true;
                    case nav_add:
                        navigationView.getMenu().getItem(0).setChecked(false);
                        navigationView.getMenu().getItem(1).setChecked(true);
                        ft = getSupportFragmentManager().beginTransaction();
                        if (!addTaskFragment.isVisible()) {
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final int action_share = R.id.action_share;
        final int action_settings = R.id.action_settings;
        switch (item.getItemId()) {
            case action_share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.setPackage("com.google.android.gm");
                ArrayList<Task> tasks = new ArrayList<Task>();
                tasks = DataBaseManager.getInstance().getAllTasks();

                String shareBody = "";
                for (int i = 0; i < tasks.size(); i++) {
                    shareBody = shareBody + "Name: " + tasks.get(i).getName() + " Date: " + tasks.get(i).getDate();
                }
                Log.d(TAG, "onOptionsItemSelected: " + shareBody);
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "my Tasks");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(shareBody));
                startActivity(sharingIntent);

                return true;
            case action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addTask(View view) {
        ft = getSupportFragmentManager().beginTransaction();
        if (addTaskFragment.isVisible()) {
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void switchBetweenDateFragment(String date, int position) {
        taskFragment.fetchTasksFromDBDependentOnDate(date);
        tasksDatesFragment.updateSelected(position);
        ft = getSupportFragmentManager().beginTransaction();
        ft.remove(taskFragment);
        ft.add(R.id.content_frame, taskFragment);
        ft.commit();
    }

}

