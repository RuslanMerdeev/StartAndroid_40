package com.merdeev.alertdialogcustom;

import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG = "States";
    private Button btnAdd, btnRemove;
    final int DIALOG = 1;
    ArrayList<TextView> textViews;
    LinearLayout view;
    TextView tvCount, tvTime;
    SimpleDateFormat sdf;
    int btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnRemove = (Button) findViewById(R.id.btnRemove);
        btnRemove.setOnClickListener(this);

        textViews = new ArrayList<>(10);

        sdf = new SimpleDateFormat("HH:mm:ss");
    }

    @Override
    public void onClick(View view) {
        btn = view.getId();
        showDialog(DIALOG);
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG) {
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle(R.string.title);
            view = (LinearLayout) getLayoutInflater().inflate(R.layout.dialog, null);
            adb.setView(view);
            tvCount = (TextView) view.findViewById(R.id.tvCount);
            tvTime = (TextView) view.findViewById(R.id.tvTime);
            return adb.create();
        }
        return super.onCreateDialog(id);
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        if(id == DIALOG) {
    //            tvTime = (TextView) dialog.getWindow().findViewById(R.id.tvTime);
            tvTime.setText(sdf.format(new Date(System.currentTimeMillis())));
            TextView tv;
            switch (btn) {
                case R.id.btnAdd:
                    tv = new TextView(this);
                    view.addView(tv, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    tv.setText("TextView " + (textViews.size() + 1));
                    textViews.add(tv);
                    break;

                case R.id.btnRemove:
                    if (textViews.size() > 0) {
                        tv = textViews.get(textViews.size() - 1);
                        view.removeView(tv);
                        textViews.remove(tv);
                    }
                    break;
            }
            tvCount.setText("Кол-во TextView = " + textViews.size());
        }
    }
}
