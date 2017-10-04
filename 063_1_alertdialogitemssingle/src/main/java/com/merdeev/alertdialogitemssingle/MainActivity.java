package com.merdeev.alertdialogitemssingle;

import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener {
    final String LOG = "States";

    final int DIALOG_ITEMS = 1;
    final int DIALOG_ADAPTER = 2;
    final int DIALOG_CURSOR = 3;
    DB db;
    Cursor cursor;
    Button btnItems, btnAdapter, btnCursor;
    String[] data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        data = getResources().getStringArray(R.array.data);
    }

    @Override
    public void onClick(View view) {
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
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        ((AlertDialog) dialog).getListView().setItemChecked(-1, true);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        switch (id) {
            case DIALOG_ITEMS:
                adb.setTitle(R.string.items);
                adb.setSingleChoiceItems(data, -1, this);
                break;

            case DIALOG_ADAPTER:
                adb.setTitle(R.string.adapter);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.select_dialog_singlechoice, data);
                adb.setSingleChoiceItems(adapter, -1, this);
                break;

            case DIALOG_CURSOR:
                adb.setTitle(R.string.cursor);
                adb.setSingleChoiceItems(cursor, -1, DB.COLUMN_TXT, this);
                break;
        }
        adb.setPositiveButton(R.string.ok, this);
        return adb.create();
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        ListView lv = ((AlertDialog) dialogInterface).getListView();
        if (i == Dialog.BUTTON_POSITIVE) {
            Log.d(LOG, "pos = " + lv.getCheckedItemPosition());
        } else {
            Log.d(LOG, "which = " + i);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
