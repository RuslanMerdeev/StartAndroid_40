package com.merdeev.alertdialogitemsmulti;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener, DialogInterface.OnMultiChoiceClickListener {

    final String LOG = "States";

    final int DIALOG_ITEMS = 1;
    final int DIALOG_CURSOR = 2;
    DB db;
    Cursor cursor;
    Button btnItems, btnCursor;
    String[] data;
    boolean[] chkd = {false, false, false, false};
    int dialog_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DB(this);
        db.open();

        cursor = db.getAllData();
        startManagingCursor(cursor);
        data = getResources().getStringArray(R.array.data);
        btnItems = (Button) findViewById(R.id.btnItems);
        btnItems.setOnClickListener(this);
        btnCursor = (Button) findViewById(R.id.btnCursor);
        btnCursor.setOnClickListener(this);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        switch (id) {
            case DIALOG_ITEMS:
                adb.setTitle(R.string.items);
                adb.setMultiChoiceItems(data, chkd, this);
                break;

            case DIALOG_CURSOR:
                adb.setTitle(R.string.cursor);
                adb.setMultiChoiceItems(cursor, DB.COLUMN_CHK, DB.COLUMN_TXT, this);
                break;
        }
        adb.setPositiveButton(R.string.ok, this);
        return adb.create();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnItems:
                dialog_type = DIALOG_ITEMS;
                showDialog(DIALOG_ITEMS);
                break;

            case R.id.btnCursor:
                dialog_type = DIALOG_CURSOR;
                showDialog(DIALOG_CURSOR);
                break;
        }
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        SparseBooleanArray sbArray = ((AlertDialog) dialogInterface).getListView().getCheckedItemPositions();
        for (int i1=0; i1<sbArray.size(); i1++) {
            int key = sbArray.keyAt(i1);
            if (sbArray.get(key)) {
                Log.d(LOG, "checked: " + key);
            }
        }
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
        switch (dialog_type) {
            case DIALOG_ITEMS:
                break;

            case DIALOG_CURSOR:
                db.changeRec(i, b);
                cursor.requery();
                break;
        }
        Log.d(LOG, "which = " + i + ", isChecked = " + b);
    }
}
