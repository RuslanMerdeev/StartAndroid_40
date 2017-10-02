package com.merdeev.alertdialogsimple;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener {

    final int DIALOG_EXIT = 1;
    Button btnExit;

    @Override
    public void onBackPressed() {
        showDialog(DIALOG_EXIT);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnExit = (Button) findViewById(R.id.btnExit);
        btnExit.setOnClickListener(this);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_EXIT) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle(R.string.exit);
            adb.setMessage(R.string.save_data);
            adb.setIcon(R.drawable.andro);
            adb.setPositiveButton(R.string.yes, this);
            adb.setNeutralButton(R.string.cancel, this);
            adb.setNegativeButton(R.string.no, this);
            adb.setCancelable(false);
            return adb.create();
        }
        return super.onCreateDialog(id);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnExit:
                showDialog(DIALOG_EXIT);
                break;
        }
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case Dialog.BUTTON_POSITIVE:
                saveData();
                finish();
                break;

            case Dialog.BUTTON_NEGATIVE:
                finish();
                break;

            case Dialog.BUTTON_NEUTRAL:
                break;
        }
    }


    void saveData() {
        Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();
    }
}