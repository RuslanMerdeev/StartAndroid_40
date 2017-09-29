package com.merdeev.timepickerdialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity implements OnClickListener, OnTimeSetListener {

    int DIALOG_TIME = 1;
    int myHour = 14;
    int myMinute = 35;
    TextView tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTime = (TextView) findViewById(R.id.tvTime);
        tvTime.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvTime:
                showDialog(DIALOG_TIME);
                break;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_TIME) {
            TimePickerDialog tpd = new TimePickerDialog(this, this, myHour, myMinute, true);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        myHour = i;
        myMinute = i1;
        tvTime.setText("Time is " + myHour + " hours " + myMinute + "minutes");
    }
}
