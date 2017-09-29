package com.merdeev.datepickerdialog;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnClickListener, OnDateSetListener {

    final int DIALOG_DATE = 1;
    int myYear = 2011;
    int myMonth = 02;
    int myDay = 03;
    TextView tvDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDate = (TextView) findViewById(R.id.tvDate);
        tvDate.setOnClickListener(this);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
            DatePickerDialog dpd = new DatePickerDialog(this, this, myYear, myMonth, myDay);
            return dpd;
        }
        return super.onCreateDialog(id);
    }

    @Override
    public void onClick(View view) {
        showDialog(DIALOG_DATE);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        myYear = i;
        myDay = i1;
        myDay = i2;
        tvDate.setText("Today is " + myDay + "/" + myMonth + "/" + myYear);
    }
}
