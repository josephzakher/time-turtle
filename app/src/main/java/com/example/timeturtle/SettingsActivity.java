package com.example.timeturtle;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.example.timeturtle.database.DataBaseManager;
import com.example.timeturtle.helperclasses.InitApplication;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "prefs";
    private static final String PREF_DARK_THEME = "dark_theme";
    boolean useDarkTheme;
    @Override
    protected void onCreate(Bundle savedInstanceState) {SharedPreferences preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        useDarkTheme = preferences.getBoolean(PREF_DARK_THEME, false);

        if (useDarkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        super.onCreate(savedInstanceState);

        Toolbar myToolbar = findViewById(R.id.toolbar_second);
        setSupportActionBar(myToolbar);

        setContentView(R.layout.activity_settings);
        SwitchMaterial toggle = (SwitchMaterial) findViewById(R.id.switch1);
        toggle.setChecked(useDarkTheme);

        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                toggleTheme(isChecked);
            }
        });

    }

    private void toggleTheme(boolean darkTheme) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putBoolean(PREF_DARK_THEME, darkTheme);
        editor.apply();
        if (darkTheme) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        recreate();
        Activity activity = MainActivity.activity;
        Intent intent =activity.getIntent();
        finish();

        startActivity(intent);

    }

    public void onDelete(View view) {
        DataBaseManager db = new DataBaseManager();
        db.clearDatabase();
        TasksDatesFragment tasksDatesFragment = new TasksDatesFragment();
        TaskFragment taskFragment = new TaskFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.remove(tasksDatesFragment);
        ft.remove(taskFragment);
        Activity activity = MainActivity.activity;
        activity.recreate();
        finish();
    }
}
