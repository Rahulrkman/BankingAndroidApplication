package com.example.bankingapp.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.bankingapp.util.Util;

public class DatabaseHandler extends SQLiteOpenHelper {
    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE "+ Util.TABLE_NAME_USER+ "("+ Util.USER_ID+ " INTEGER,"+
                Util.USER_PHONE_NUMBER+ " TEXT,"+ Util.USER_NAME+ " TEXT, "+ Util.USER_BALANCE+ " INTEGER, "+ Util.USER_EMAIL+ " TEXT)";
        String CREATE_HISTORY_TABLE = "CREATE TABLE "+ Util.TABLE_NAME_HISTORY+ "("+ Util.HISTORY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                Util.HISTORY_DATE+ " TEXT,"+ Util.HISTORY_FROM+ " TEXT, "+ Util.HISTORY_TO+ " TEXT, "+ Util.HISTORY_AMOUNT+ " INTEGER)";
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_HISTORY_TABLE);
        db.execSQL("INSERT INTO "+Util.TABLE_NAME_USER+" VALUES(1,'8652517426', 'Rahul', 500000, 'rahul@gmail.com')");
        db.execSQL("INSERT INTO "+Util.TABLE_NAME_USER+" VALUES(2,'8652876386', 'Rushank', 500000, 'rushank@gmail.com')");
        db.execSQL("INSERT INTO "+Util.TABLE_NAME_USER+" VALUES(3,'8452809311', 'Mitesh', 400000, 'mitesh@gmail.com')");
        db.execSQL("INSERT INTO "+Util.TABLE_NAME_USER+" VALUES(4,'8108194395', 'Joel', 600000, 'joel@gmail.com')");
        db.execSQL("INSERT INTO "+Util.TABLE_NAME_USER+" VALUES(5,'7208532889', 'Vedesh', 72000, 'vedesh@gmail.com')");
        db.execSQL("INSERT INTO "+Util.TABLE_NAME_USER+" VALUES(6,'9168839412', 'Gargi', 86000, 'gargi@gmail.com')");
        db.execSQL("INSERT INTO "+Util.TABLE_NAME_USER+" VALUES(7,'8612345678', 'Vinayak', 150000, 'vinu@gmail.com')");
        db.execSQL("INSERT INTO "+Util.TABLE_NAME_USER+" VALUES(8,'8805788000', 'Chaitanya', 290000, 'chaitya@gmail.com')");
        db.execSQL("INSERT INTO "+Util.TABLE_NAME_USER+" VALUES(9,'9987200986', 'Kartik', 380000, 'kartik@gmail.com')");
        db.execSQL("INSERT INTO "+Util.TABLE_NAME_USER+" VALUES(10,'8169633253', 'Aadarsh', 320000, 'aadarsh@gmail.com')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Util.TABLE_NAME_USER);
        db.execSQL("DROP TABLE IF EXISTS "+Util.TABLE_NAME_HISTORY);
        onCreate(db);
    }

    public Cursor allData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Util.TABLE_NAME_USER, null);
        return cursor;
    }

    public void updateFromBalance(String name, int newFromBalance, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.USER_BALANCE, newFromBalance);

        db.update(Util.TABLE_NAME_USER, contentValues, Util.USER_NAME+ "=?", new String[]{name});
    }

    public void updateToBalance(String name, int newToBalance, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.USER_BALANCE, newToBalance);

        db.update(Util.TABLE_NAME_USER, contentValues, Util.USER_NAME+ "=?", new String[]{name});
    }

    public void addTransferHistory(String date, String from, String to, int amount, SQLiteDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.HISTORY_DATE, date);
        contentValues.put(Util.HISTORY_FROM, from);
        contentValues.put(Util.HISTORY_TO, to);
        contentValues.put(Util.HISTORY_AMOUNT, amount);

        db.insert(Util.TABLE_NAME_HISTORY, null, contentValues);
    }

    public Cursor allHistoryData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+Util.TABLE_NAME_HISTORY, null);
        return cursor;
    }

//    public void deleteAll(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.execSQL("DELETE FROM "+Util.TABLE_NAME_HISTORY);
//    }

}
