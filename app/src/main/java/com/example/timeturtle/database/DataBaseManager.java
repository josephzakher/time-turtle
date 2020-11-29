package com.example.timeturtle.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.timeturtle.controllers.AppController;
import com.example.timeturtle.helperclasses.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DataBaseManager {

    private static final DataBaseManager instance = new DataBaseManager();
    private static final String TAG = "DB";
    private DataBaseConnector databaseConnector;

    public DataBaseManager() {
        databaseConnector = new DataBaseConnector(AppController.getInstance().getApplicationContext());
    }

    public static DataBaseManager getInstance() {
        return instance;
    }

    public void insertTask(Task task){
        SQLiteDatabase db = databaseConnector.getWritableDatabase();
        ContentValues taskValues = new ContentValues();
        taskValues.put( DataBaseContract.DataBaseEntry.COLUMN_NAME_DATE,task.getDate());
        taskValues.put( DataBaseContract.DataBaseEntry.COLUMN_NAME_TIME,task.getTime());
        taskValues.put( DataBaseContract.DataBaseEntry.COLUMN_NAME_NAME,task.getName());
        taskValues.put( DataBaseContract.DataBaseEntry.COLUMN_NAME_DESCRIPTION,task.getDescription());
        db.insert( DataBaseContract.DataBaseEntry.TABLE_NAME,null,taskValues);
    }
    public ArrayList<Task> getAllTasks() {
        String selectQuery = "SELECT * FROM TASK";
        SQLiteDatabase db = databaseConnector.getWritableDatabase();
        db.beginTransaction();
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    Task task = new Task(
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4));
                    tasks.add(task);
                } while (cursor.moveToNext());
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return tasks;
    }
    public ArrayList<Task> getTasksPerDay(String date) {
        String selectQuery = "SELECT * FROM TASK WHERE DATE='"+date.trim()+"'";
        SQLiteDatabase db = databaseConnector.getWritableDatabase();
        db.beginTransaction();
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    Task task = new Task(
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4));
                    tasks.add(task);
                } while (cursor.moveToNext());
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        Log.d(TAG, "getTasksPerDay: "+tasks.toString());
        return tasks;
    }
    public ArrayList<String> getAllTasksDates() {
        String selectQuery = "SELECT DISTINCT DATE FROM TASK";
        SQLiteDatabase db = databaseConnector.getWritableDatabase();
        db.beginTransaction();
        ArrayList<String> dates = new ArrayList<String>();
        try {
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    String temp = cursor.getString(0);
                    dates.add(temp);
                } while (cursor.moveToNext());
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
        return dates;
    }
}
