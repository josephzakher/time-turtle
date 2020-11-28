package com.example.timeturtle.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.timeturtle.database.DataBaseContract.SQL_CREATE_ENTRIES;
import static com.example.timeturtle.database.DataBaseContract.SQL_DELETE_ENTRIES;

public class DataBaseConnector extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "TimeTurtle.db";
    private static final int DATABASE_VERSION = 1;

    DataBaseConnector(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
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
