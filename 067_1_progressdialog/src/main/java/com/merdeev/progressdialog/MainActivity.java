package com.merdeev.progressdialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, DialogInterface.OnClickListener {

    Button btnDefault, btnHoriz;
    ProgressDialog pd;
    Handler h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDefault = (Button) findViewById(R.id.btnDefault);
        btnDefault.setOnClickListener(this);
        btnHoriz = (Button) findViewById(R.id.btnHoriz);
        btnHoriz.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDefault:
                pd = new ProgressDialog(this);
                pd.setTitle("Title");
                pd.setMessage("Message");
                pd.setButton(Dialog.BUTTON_POSITIVE, "OK", this);
                pd.show();
                break;

            case R.id.btnHoriz:
                pd = new ProgressDialog(this);
                pd.setTitle("Title");
                pd.setMessage("Message");
                pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pd.setMax(2148);
                pd.setIndeterminate(true);
                pd.show();
                h = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        pd.setIndeterminate(false);
                        if (pd.getProgress() < pd.getMax()) {
                            pd.incrementProgressBy(50);
                            pd.incrementSecondaryProgressBy(75);
                            h.sendEmptyMessageDelayed(0, 100);
                        }
                        else {
                            pd.dismiss();
                        }
                    }
                };
                h.sendEmptyMessageDelayed(0, 2000);
                break;
        }
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        switch (i) {
            case Dialog.BUTTON_POSITIVE:

                break;
        }
    }
}
