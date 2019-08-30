package com.linson.android.simplemusic.DAL;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MUSICDB extends SQLiteOpenHelper
{
    public static int defaultVersion=1;
    public MUSICDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql_create1=new String("create table SongsList('SL_ID' INTEGER PRIMARY KEY AUTOINCREMENT,'SL_name' text,SL_Songs text);");
        String sql_create2=new String("create table localmusic ('id' integer primary key autoincrement  , 'musicName' text,'artist' text,'duration' integer,'path' text, 'songId' integer );");
        String sql_create3=new String("create table Song ('id' integer primary key autoincrement  ,'musicName' text,'artist' text,'duration' integer,'path' text, 'songId' integer ,'version' integer);");
        String sql_create4=new String("create table list_song (id integer primary key autoincrement,sl_id integer,sid integer)");
        db.execSQL(sql_create1);
        db.execSQL(sql_create2);
        db.execSQL(sql_create3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }

    public static MUSICDB getInstance(Context context)
    {
        return new MUSICDB(context, "mp3db", null, defaultVersion);
    }
}