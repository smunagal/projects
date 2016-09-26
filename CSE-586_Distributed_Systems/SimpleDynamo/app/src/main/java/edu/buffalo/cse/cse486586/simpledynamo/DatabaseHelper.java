package edu.buffalo.cse.cse486586.simpledynamo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by shiva on 3/21/16.
 * References : http://developer.android.com/training/basics/data-storage/databases.html
 */


public class DatabaseHelper extends SQLiteOpenHelper{
    // Database details  , DB name is GROUPTABLE.db, View is GROUP_TABLE
    public static final String DATABASE_NAME = "GROUPTABLE.db";
    public static final String TABLE_NAME = "GROUP_TABLE";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,1);
        // Opening writable mode ,i.e DB will be created(if not exists) when app launches.
        // SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "+ TABLE_NAME + "(" +
                "key TEXT PRIMARY KEY NOT NULL," +
                "value TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}