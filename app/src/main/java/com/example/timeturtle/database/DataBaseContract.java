package com.example.timeturtle.database;

import android.provider.BaseColumns;

public final class DataBaseContract {
    private DataBaseContract() {
    }

    public static class DataBaseEntry implements BaseColumns {
        public static final String TABLE_NAME = "TASK";
        public static final String COLUMN_NAME_DATE = "DATE";
        public static final String COLUMN_NAME_TIME = "TIME";
        public static final String COLUMN_NAME_NAME = "NAME";
        public static final String COLUMN_NAME_DESCRIPTION = "DESCRIPTION";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DataBaseEntry.TABLE_NAME + " (" +
                    DataBaseEntry._ID + " INTEGER PRIMARY KEY," +
                    DataBaseEntry.COLUMN_NAME_DATE + " TEXT," +
                    DataBaseEntry.COLUMN_NAME_TIME + " TEXT," +
                    DataBaseEntry.COLUMN_NAME_NAME + " TEXT," +
                    DataBaseEntry.COLUMN_NAME_DESCRIPTION + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DataBaseEntry.TABLE_NAME;
}