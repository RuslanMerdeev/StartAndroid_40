package com.merdeev.alertdialogprepare;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final int DIALOG = 1;
    final String LOG = "States";
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    Button btnHello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHello = (Button) findViewById (R.id.btnHello);
        btnHello.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        showDialog(DIALOG);
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        Log.d(LOG, "onPrepareDialog");
        if (id == DIALOG) {
            ((AlertDialog)dialog).setMessage(sdf.format(new Date(System.currentTimeMillis())));
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        Log.d(LOG, "onCreateDialog");
        if (id == DIALOG) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle(R.string.title);
            adb.setMessage(sdf.format(new Date(System.currentTimeMillis())));
            return adb.create();
        }
        return super.onCreateDialog(id);
    }
}
