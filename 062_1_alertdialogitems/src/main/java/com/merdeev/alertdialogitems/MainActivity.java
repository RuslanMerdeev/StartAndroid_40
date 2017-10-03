package com.merdeev.alertdialogitems;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener {

    final String LOG = "States";

    final int DIALOG_ITEMS = 1;
    final int DIALOG_ADAPTER = 2;
    final int DIALOG_CURSOR = 3;
    int cnt = 0;
    DB db;
    Cursor cursor;

    String[] data;
    Button btnItems, btnAdapter, btnCursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        data = getResources().getStringArray(R.array.data);
        db = new DB(this);
        db.open();
        cursor = db.getAllData();
        startManagingCursor(cursor);

        btnItems = (Button) findViewById(R.id.btnItems);
        btnItems.setOnClickListener(this);
        btnAdapter = (Button) findViewById(R.id.btnAdapter);
        btnAdapter.setOnClickListener(this);
        btnCursor = (Button) findViewById(R.id.btnCursor);
        btnCursor.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        changeCount();
        switch (view.getId()) {
            case R.id.btnItems:
                showDialog(DIALOG_ITEMS);
                break;

            case R.id.btnAdapter:
                showDialog(DIALOG_ADAPTER);
                break;

            case R.id.btnCursor:
                showDialog(DIALOG_CURSOR);
                break;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        switch (id) {
            case DIALOG_ITEMS:
                adb.setTitle(R.string.items);
                adb.setItems(data, this);
                break;

            case DIALOG_ADAPTER:
                adb.setTitle(R.string.adapter);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, data);
                adb.setAdapter(adapter, this);
                break;

            case DIALOG_CURSOR:
                adb.setTitle(R.string.cursor);
                adb.setCursor(cursor, this, DB.COLUMN_TXT);
                break;
        }
        return adb.create();
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        AlertDialog aDialog = (AlertDialog) dialog;
        ListAdapter lAdapter = aDialog.getListView().getAdapter();
        if (id == DIALOG_ADAPTER) {
            if (lAdapter instanceof BaseAdapter) {
                BaseAdapter bAdapter = (BaseAdapter) lAdapter;
                bAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        Log.d(LOG, "which = " + i);
        int item = i;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }

    void changeCount() {
        cnt++;
        data[3] = String.valueOf(cnt);
        db.changeRec(3, String.valueOf(cnt));
        cursor.requery();
    }
}
