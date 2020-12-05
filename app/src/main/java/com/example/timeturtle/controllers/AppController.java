package com.example.timeturtle.controllers;

import android.app.Application;

import com.example.timeturtle.helperclasses.InitApplication;

public class AppController extends InitApplication {
    private static AppController instance = null;
    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }
    public static AppController getInstance(){return instance;}
}
