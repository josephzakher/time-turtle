package com.example.timeturtle.controllers;

import android.app.Application;

public class AppController extends Application {
    private static AppController instance = null;
    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }
    public static AppController getInstance(){return instance;}
}
