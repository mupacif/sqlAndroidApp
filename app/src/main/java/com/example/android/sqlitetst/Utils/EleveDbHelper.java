package com.example.android.sqlitetst.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import  com.example.android.sqlitetst.Utils.EleveContract.*;
/**
 * Created by mupac_000 on 14-02-17.
 */

public class EleveDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_BASE="eleve.db";
    private static final int DATABASE_VERSION=1;
    public EleveDbHelper(Context context)
    {
        super(context,DATABASE_BASE,null,DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase sql)
    {
        final String SQL_CREATE_ELEVE  = "CREATE TABLE "+EleveEntry.TABLE_NAME+ "("+
                                EleveEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                                EleveEntry.COLUMN_NOM+" TEXT NOT NULL,"+
                                EleveEntry.COLUMN_NOTE+" INTEGER NOT NULL,"+
                                EleveEntry.COLUMN_TIMESTAMP+" INTEGER NOT NULL"+
                                ");";
    }


}
