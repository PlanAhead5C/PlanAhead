package com.example.planahead5c;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "planAhead.db";
    public static final String TABLE_NAME = "statistics";
    public static final String TABLE_EVENTS = "events";
    public static final String TABLE_REMINDER = "reminders";
    public static final String TABLE_MODULE = "modules";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
      // SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +TABLE_NAME+" (StatsID INTEGER PRIMARY KEY AUTOINCREMENT, Efficiency INT," +
                " Sleep INT, Attendance INT, Study INT)");
        db.execSQL("create table " +TABLE_EVENTS+" (eventID INTEGER PRIMARY KEY AUTOINCREMENT," +
                " eventName TEXT," +
                " eventTime INTEGER," +
                " eventDate TEXT," +
                " eventType TEXT," +
                " eventModule TEXT," +
                " eventLocation TEXT," +
                " eventPriority INTEGER," +
                " eventFrequency INTEGER," +
                " eventColour TEXT," +
                " eventLength TEXT)");
        db.execSQL("create table "+TABLE_REMINDER+" (ReminderID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " ReminderTime INTEGER, ReminderDate TEXT, EventsID INTEGER, " +
                " FOREIGN KEY (EventsID) REFERENCES "+TABLE_EVENTS+" (eventID))");
        db.execSQL("create table "+TABLE_MODULE+" (ModuleID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " moduleName TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_EVENTS);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_REMINDER);
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_MODULE);
        onCreate(db);
    }

    public boolean insertStats(String efficiency, String attendance, String study, String sleep){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Efficiency", efficiency);
        contentValues.put("Attendance", attendance);
        contentValues.put("Study", study);
        contentValues.put("Sleep", sleep);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean insertEvents(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("eventName", name);
        long result = db.insert(TABLE_EVENTS, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }
}
