package com.jkm.hellocab;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by HP on 11-06-2018.
 */

public class DB extends SQLiteOpenHelper {
    public static final String dbn="PROFILE.db";
    public static final String tbn="DATA";
    public static final String c1="FName";
    public static final String c2="LName";
    public static final String c3="EID";
    public static final String c4="Password";

    public DB(Context context)
    {
        super(context,dbn,null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("Create table "+(tbn)+" (FName TEXT,LName TEXT,EID TEXT PRIMARY KEY UNIQUE,Password TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+tbn);
        onCreate(db);
    }
    public Boolean dataInsert(String fn,String ln,String eid,String p)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(c1,fn);
        cv.put(c2,ln);
        cv.put(c3,eid);
        cv.put(c4,p);
        long result=db.insert(tbn,null,cv);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor dataShow()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+tbn,null);
        return cursor;
    }
}
