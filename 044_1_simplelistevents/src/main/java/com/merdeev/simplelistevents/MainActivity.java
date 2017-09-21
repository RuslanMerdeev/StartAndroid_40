package com.merdeev.simplelistevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements OnItemClickListener, OnItemSelectedListener, OnScrollListener {

    final String LOG_TAG = "States";
    ListView lvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvMain = (ListView) findViewById(R.id.lvMain);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.names, android.R.layout.simple_list_item_1);
        lvMain.setAdapter(adapter);

        lvMain.setOnItemClickListener(this);
        lvMain.setOnItemSelectedListener(this);
        lvMain.setOnScrollListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d(LOG_TAG, "ItemClick: position = " + i + " , id = " + l);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d(LOG_TAG, "ItemSelect: position = " + i + " , id = " + l);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.d(LOG_TAG, "ItemSelect: nothing");
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {
        Log.d(LOG_TAG, "ScrollState = " + i);
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {
//        Log.d(LOG_TAG, "Scroll: firstVisibleItem = " + i + ", visibleItemCount = " + i1 + ", totalItemCount = " + i2);
    }
}
