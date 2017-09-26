package com.merdeev.simpleadaptercustom2;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;
import android.widget.SimpleAdapter.ViewBinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements ViewBinder {

    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_PB = "pb";
    final String ATTRIBUTE_NAME_LL = "ll";

    ListView lvSimple;

    int red, orange, green;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        red = ContextCompat.getColor(this, R.color.Red);
        orange = ContextCompat.getColor(this, R.color.Orange);
        green = ContextCompat.getColor(this, R.color.Green);

        int[] load = getResources().getIntArray(R.array.load);

        ArrayList<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> m;

        for(int i=0; i<load.length; i++) {
            m = new HashMap<String, Object>();
            m.put(ATTRIBUTE_NAME_TEXT, "Day " + (i + 1) + ". Load: " + load[i] + "%");
            m.put(ATTRIBUTE_NAME_PB, load[i]);
            m.put(ATTRIBUTE_NAME_LL, load[i]);
            data.add(m);
        }

        String[] from = {ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_PB, ATTRIBUTE_NAME_LL};
        int[] to = {R.id.tvLoad, R.id.pbLoad, R.id.llLoad};

        SimpleAdapter sAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);
        sAdapter.setViewBinder(this);

        lvSimple = (ListView) findViewById(R.id.lvSimple);
        lvSimple.setAdapter(sAdapter);
    }

    @Override
    public boolean setViewValue(View view, Object o, String s) {
        int i = 0;
        switch (view.getId()) {
            case R.id.llLoad:
                i = ((Integer) o).intValue();
                if (i < 40) view.setBackgroundColor(green);
                else if (i < 70) view.setBackgroundColor(orange);
                else view.setBackgroundColor(red);
                return true;

            case R.id.pbLoad:
                i = ((Integer) o).intValue();
                ((ProgressBar) view).setProgress(i);
                return true;
        }
        return false;
    }
}
