package com.example.manon.appli_mobile_bdd_manon_hassnaoui;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.provider.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manon on 15/03/2017.
 */

public class ChapterDataSource {

    // Database fields
    private SQLiteDatabase database;
    private Mysqlite dbHelper;
    private String[] allColumns={Mysqlite.COLUMN_ID,
            Mysqlite.COLUMN_NAME, Mysqlite.COLUMN_DESCRIPTION};

    public ChapterDataSource(Context context)
    {
        dbHelper=new Mysqlite(context);
    }

    public void open() throws SQLException
    {
        database=dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
    }

    //créée une chapitre
    public Chapter createChapter(String name, String description)
    {
        ContentValues values = new ContentValues();
        values.put(Mysqlite.COLUMN_NAME, name);
        values.put(Mysqlite.COLUMN_DESCRIPTION, description);
        long insertId = database.insertOrThrow(Mysqlite.TABLE_CHAPTER, null, values);

        Cursor cursor = database.query(Mysqlite.TABLE_CHAPTER, allColumns, Mysqlite.COLUMN_ID+" = "+insertId, null, null, null, null);

        cursor.moveToFirst();
        Chapter newChapter = cursorToChapter(cursor);
        cursor.close();

        return  newChapter;
    }

    //supprime un chapitre
    public void deleteChapter(Chapter chapter)
    {
        long id = chapter.getId();
        System.out.println("Chapter deleted with id: "+ id);
        database.delete(Mysqlite.TABLE_CHAPTER, Mysqlite.COLUMN_ID+" = "+id, null);
    }

    public List<Chapter> getAllChapter()
    {
        List<Chapter> chapters = new ArrayList<Chapter>();

        Cursor cursor = database.query(Mysqlite.TABLE_CHAPTER, allColumns, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Chapter chapter = cursorToChapter(cursor);
            chapters.add(chapter);
            cursor.moveToNext();
        }
        cursor.close();
        return chapters;
    }

    //curseur
    private Chapter cursorToChapter(Cursor cursor)
    {
        Chapter chapter = new Chapter();
        chapter.setId(cursor.getLong(0));
        chapter.setName(cursor.getString(1));
        chapter.setDescription(cursor.getString(2));

        return chapter;
    }
}
