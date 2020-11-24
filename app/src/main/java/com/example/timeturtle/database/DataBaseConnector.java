package com.example.timeturtle.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.timeturtle.helperclasses.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.timeturtle.database.DataBaseContract.SQL_CREATE_ENTRIES;
import static com.example.timeturtle.database.DataBaseContract.SQL_DELETE_ENTRIES;

public class DataBaseConnector extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TimeTurtle.db";
    private static final int DATABASE_VERSION = 1;

    DataBaseConnector(Context context){ super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_ENTRIES);
//        this.db = db;
//
//        db.execSQL("CREATE TABLE TASK ("
//
//                + "STARTDATE TEXT,"
//                + "ENDDATE TEXT,"
//                + "NAME TEXT,"
//                + "DESCRIPTION TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
