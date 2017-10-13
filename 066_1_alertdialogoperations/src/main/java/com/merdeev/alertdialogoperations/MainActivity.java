package com.merdeev.alertdialogoperations;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnShowListener, DialogInterface.OnCancelListener, DialogInterface.OnDismissListener {

    final static String LOG = "States";
    final int DIALOG = 1;
    Button button1;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                showDialog(DIALOG);
                Handler handler = new Handler();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        method1();
                    }
                }, 2000);

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        method2();
                    }
                }, 4000);
                break;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG) {
            Log.d(LOG, "Create");
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Title");
            adb.setMessage("Message");
            adb.setPositiveButton("OK", null);
            dialog = adb.create();
            dialog.setOnShowListener(this);
            dialog.setOnCancelListener(this);
            dialog.setOnDismissListener(this);
            return dialog;
        }
        return super.onCreateDialog(id);
    }

    @Override
    public void onShow(DialogInterface dialogInterface) {
        Log.d(LOG, "Show");
    }

    @Override
    public void onCancel(DialogInterface dialogInterface) {
        Log.d(LOG, "Cancel");
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        Log.d(LOG, "Dismiss");
    }

    void method1() {
//        dismissDialog(DIALOG);
        removeDialog(DIALOG);
    }

    void method2() {
//        showDialog(DIALOG);
    }
}
