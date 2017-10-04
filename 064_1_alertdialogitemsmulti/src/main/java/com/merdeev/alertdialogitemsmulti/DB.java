package com.merdeev.alertdialogitemsmulti;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by r.merdeev on 03.10.2017.
 */

public class DB {
    private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "mytab";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_CHK = "checked";
    public static final String COLUMN_TXT = "txt";

    private static final String DB_CREATE = "create table " + DB_TABLE + "(" + COLUMN_ID + " integer primary key, " + COLUMN_CHK + " integer," + COLUMN_TXT + " text" + ");";
    private final Context mCtx;
    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;

    public DB(Context mCtx) {
        this.mCtx = mCtx;
    }

    public void open() {
        mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }

    public void close() {
        if (mDBHelper != null) {
            mDBHelper.close();
        }
    }

    public Cursor getAllData() {
        return mDB.query(DB_TABLE, null, null, null, null, null, null);
    }

    public void changeRec(int id, Boolean ch) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_CHK, ch?1:0);
        mDB.update(DB_TABLE, cv, COLUMN_ID + " = " + id, null);
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(DB_CREATE);

            String[] some = mCtx.getResources().getStringArray(R.array.some);
            ContentValues cv = new ContentValues();
            for (int i=0; i<4; i++) {
                cv.put(COLUMN_ID, i);
                cv.put(COLUMN_CHK, 0);
                cv.put(COLUMN_TXT, some[i]);
                sqLiteDatabase.insert(DB_TABLE, null, cv);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}
