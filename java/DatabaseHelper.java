package com.example.cafit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "USERS.db";
    public static final String TABLE_NAME = "USERS_TABLE";
    public static final String COL1 = "ID";
    public static final String COL2 = "USERNAME";
    public static final String COL3 = "NAME";
    public static final String COL4 = "AGE";
    public static final String COL5 = "SEX";
    public static final String COL6 = "PASSWORD";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, NAME TEXT, AGE INTEGER, SEX TEXT, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String username, String name, String age, String sex, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, username);
        contentValues.put(COL3, name);
        contentValues.put(COL4, age);
        contentValues.put(COL5, sex);
        contentValues.put(COL6, password);
        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("Select * from " + TABLE_NAME + " where username = ?", new String[]{username});
        return result.getCount() != 0;
    }

    public boolean verifyAccount(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME + " where username = ? and password = ?", new String[]{username, password});
        return cursor.getCount() > 0;
    }
}
