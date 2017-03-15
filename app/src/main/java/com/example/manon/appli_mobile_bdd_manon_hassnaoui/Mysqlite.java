package com.example.manon.appli_mobile_bdd_manon_hassnaoui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Manon on 15/03/2017.
 */

public class Mysqlite extends SQLiteOpenHelper {

    public static final String TABLE_CHAPTER = "chapter";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";

    private static final String DATABASE_NAME="chapter.db";
    private static final int DATABASE_VERSION=1;

    //private static final String statement
    private static final String DATABASE_CREATE = "create table " + TABLE_CHAPTER + "(" +COLUMN_ID + " integer primary key autoincrement, "+ COLUMN_NAME + " text not null, " +  COLUMN_DESCRIPTION + " text not null ); ";

    public Mysqlite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(Mysqlite.class.getName(), "Upgrading database from version " + oldVersion +"to " + newVersion+", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CHAPTER);
        onCreate(db);

    }
}
