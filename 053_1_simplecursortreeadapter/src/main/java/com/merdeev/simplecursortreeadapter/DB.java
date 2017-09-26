package com.merdeev.simplecursortreeadapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by r.merdeev on 26.09.2017.
 */

public class DB {
    private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 1;

    private static final String GROUP_TABLE = "groups";
    public static final String GROUP_COLUMN_ID = "_id";
    public static final String GROUP_COLUMN_NAME = "name";
    private static final String GROUP_TABLE_CREATE = "create table "
            + GROUP_TABLE + "(" + GROUP_COLUMN_ID
            + " integer primary key autoincrement, " + GROUP_COLUMN_NAME + " text" + ");";

    private static final String CHILD_TABLE = "childs";
    public static final String CHILD_COLUMN_ID = "_id";
    public static final String CHILD_COLUMN_NAME = "name";
    public static final String CHILD_COLUMN_GROUP = "groups";
    private static final String CHILD_TABLE_CREATE = "create table "
            + CHILD_TABLE + "(" + CHILD_COLUMN_ID
            + " integer primary key autoincrement, " + CHILD_COLUMN_NAME
            + " text, " + CHILD_COLUMN_GROUP + " text" + ");";

    private final Context mCtx;

    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;

    public DB(Context ctx) {
        mCtx = ctx;
    }

    public void open() {
        mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }

    public void close() {
        if (mDBHelper != null)
            mDBHelper.close();
    }

    public Cursor getGroupData() {
        return mDB.query(GROUP_TABLE, null, null, null, null, null, null);
    }

    public Cursor getChildData(String group) {
        return mDB.query(CHILD_TABLE, null, CHILD_COLUMN_GROUP + " = '"
                + group + "'", null, null, null, null);
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            ContentValues cv = new ContentValues();

            String[] groups = mCtx.getResources().getStringArray(R.array.groups);

            db.execSQL(GROUP_TABLE_CREATE);
            for (int i = 0; i < groups.length; i++) {
                cv.put(GROUP_COLUMN_NAME, groups[i]);
                db.insert(GROUP_TABLE, null, cv);
            }

            String[] childsHTC = mCtx.getResources().getStringArray(R.array.childsHTC);
            String[] childsSams = mCtx.getResources().getStringArray(R.array.childsSams);
            String[] childsLG = mCtx.getResources().getStringArray(R.array.childsLG);

            db.execSQL(CHILD_TABLE_CREATE);
            cv.clear();
            for (int i = 0; i < childsHTC.length; i++) {
                cv.put(CHILD_COLUMN_GROUP, groups[0]);
                cv.put(CHILD_COLUMN_NAME, childsHTC[i]);
                db.insert(CHILD_TABLE, null, cv);
            }
            for (int i = 0; i < childsSams.length; i++) {
                cv.put(CHILD_COLUMN_GROUP, groups[1]);
                cv.put(CHILD_COLUMN_NAME, childsSams[i]);
                db.insert(CHILD_TABLE, null, cv);
            }
            for (int i = 0; i < childsLG.length; i++) {
                cv.put(CHILD_COLUMN_GROUP, groups[2]);
                cv.put(CHILD_COLUMN_NAME, childsLG[i]);
                db.insert(CHILD_TABLE, null, cv);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

}
